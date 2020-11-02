package com.example.stoom.service;

import com.example.stoom.dao.IAddressDao;
import com.example.stoom.entity.Address;
import com.example.stoom.model.AddressRequest;
import com.example.stoom.model.GenericAPIResponse;
import com.example.stoom.model.GeoLocalizationResponse;
import com.example.stoom.model.ManageGeolocalizationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService {
    private final IAddressDao _addressDao;
    private final ModelMapper _mapper;
    private final IGeolocalizationService _geolocalizationService;

    @Autowired
    public AddressService(@Qualifier("postgre") IAddressDao addressDao, ModelMapper mapper, IGeolocalizationService geolocalizationService) {
        _addressDao = addressDao;
        _mapper = mapper;
        _geolocalizationService = geolocalizationService;
    }

    @Override
    public List<Address> get() {
        return _addressDao.get();
    }

    @Override
    public Optional<Address> get(Long id) {
        return _addressDao.get(id);
    }

    @Override
    public GenericAPIResponse insert(AddressRequest request) {

        GenericAPIResponse response = new GenericAPIResponse();

        Address address = _mapper.map(request, Address.class);

        ManageGeolocalizationResponse manageGeolocalizationResponse = manageGeolocalization(address);

        if (manageGeolocalizationResponse.getWasNeedToSearch() && !manageGeolocalizationResponse.isSuccess()) {
            response.addMessage(String.format("Aviso: Com o endereço informado não foi possivel obter a geolocalização com o erro: %s", manageGeolocalizationResponse.getStatusCode()));
        }

        Optional<Integer> idInserted = _addressDao.insert(address);

        if (idInserted.isPresent()) {
            response.addMessage(String.format("Endereço inserido com sucesso! Id: %s", idInserted.get()));
        } else {
            response.addError("Houve uma falha ao inserir o endereço");
        }

        return response;
    }

    @Override
    public GenericAPIResponse update(Long id, AddressRequest request) {

        GenericAPIResponse response = new GenericAPIResponse();

        Optional<Address> addressAlreadyExists = _addressDao.get(id);
        if (addressAlreadyExists.isPresent()) {
            Address address = _mapper.map(request, Address.class);
            address.setId(id);

            _addressDao.update(address);

            response.addMessage(String.format("Endereço atualizado com sucesso! Id: %d", id));
        } else {
            response.addError(String.format("Endereço com o Id: %d não encontrado!", id));
        }

        return response;
    }

    @Override
    public GenericAPIResponse delete(Long id) {

        GenericAPIResponse response = new GenericAPIResponse();

        Optional<Address> addressAlreadyExists = _addressDao.get(id);

        if (addressAlreadyExists.isPresent()) {

            _addressDao.delete(id);

            response.addMessage(String.format("Endereço exluido com sucesso! Id: %d", id));

        } else {
            response.addError(String.format("Endereço com o Id: %d não encontrado!", id));
        }

        return response;
    }


    private ManageGeolocalizationResponse manageGeolocalization(Address address) {
        ManageGeolocalizationResponse response = new ManageGeolocalizationResponse();

        if (address.getLatitude().isBlank() && address.getLongitude().isBlank()) {
            GeoLocalizationResponse geoResponse = _geolocalizationService.get(address);

            if (geoResponse.getSuccess()) {
                address.setLatitude(geoResponse.getLatitude());
                address.setLongitude(geoResponse.getLongitude());
            }

            response.setWasNeedToSearch(true);
            response.setStatusCode(geoResponse.getStatusCode());
        }

        return response;
    }
}
