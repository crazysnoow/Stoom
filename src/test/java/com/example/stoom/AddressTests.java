package com.example.stoom;

import com.example.stoom.dao.AddressDao;
import com.example.stoom.entity.Address;
import com.example.stoom.model.AddressRequest;
import com.example.stoom.model.GenericAPIResponse;
import com.example.stoom.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressTests {

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressDao addressDao;


    @Test
    public void getAddressTest() {

        when(addressDao.get()).thenReturn(Stream
                .of(new Address((long) 1, "rua 1", "123", "1", "bairro 1", "cidade 1", "estado 1", "pais 1", "cep 1", "latitude", "longitude"),
                        new Address((long) 1, "rua 1", "123", "1", "bairro 1", "cidade 1", "estado 1", "pais 1", "cep 1", "latitude", "longitude"))
                .collect(Collectors.toList()));

        assertEquals(2, addressService.get().size());
    }

    @Test
    public void getUserByIDTest() {

        Long id = (long) 1;

        Optional<Address> address = Optional.ofNullable(new Address((long) 1, "rua 1", "123", "1", "bairro 1", "cidade 1", "estado 1", "pais 1", "cep 1", "latitude", "longitude"));

        when(addressDao.get(id)).thenReturn(address);

        assertEquals(address, addressService.get(id));
    }

	@Test
	public void insertAddressWithoutGeoTest() {
		AddressRequest addressRequest = new AddressRequest("rua sucesso", "122", "1", "Jardim Boer", "Americana", "SP", "Brasil", "13477704", "", "");

		when(addressDao.insert(argThat(a -> !a.getLatitude().isBlank() && !a.getLongitude().isBlank()))).thenReturn(Optional.of(1));

		GenericAPIResponse response = addressService.insert(addressRequest);

		assertEquals(true, response.getSuccess());
	}

	@Test
	public void insertWrongAddressWithoutGeoTest() {
		AddressRequest addressRequest = new AddressRequest("rua123", "124124", "1", "J123123", "2", "asd", "gasga", "sad", "", "");

		when(addressDao.insert(argThat(a -> a.getLatitude().isBlank() && a.getLongitude().isBlank()))).thenReturn(Optional.of(1));

		GenericAPIResponse response = addressService.insert(addressRequest);

		assertEquals(true, response.getSuccess());
	}

	@Test
	public void deleteAddressTest() {
		Long id = (long) 1;

		when(addressDao.get(id)).thenReturn(Optional.of(Mockito.mock(Address.class)));

		addressService.delete(id);

		verify(addressDao, times(1)).delete(id);
	}

	@Test
	public void updateAddressTest() {
		Long id = (long) 1;
		Optional<Address> address = Optional.of(Mockito.mock(Address.class));
		AddressRequest addressRequest = Mockito.mock(AddressRequest.class);

		when(addressDao.get(id)).thenReturn(address);

		GenericAPIResponse response =  addressService.update(id , addressRequest);

		ArgumentCaptor<Address> argument = ArgumentCaptor.forClass(Address.class);
		verify(addressDao).update(argument.capture());

		boolean condition = (argument.getValue().getId() == id) && response.getSuccess();

		assertEquals(true, condition);
	}


}

