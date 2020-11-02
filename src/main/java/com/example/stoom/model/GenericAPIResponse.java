package com.example.stoom.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericAPIResponse {

    private List<String> messages;
    private boolean success;
    private List<String> errors;

    public GenericAPIResponse() {
        errors = new ArrayList<String>();
        messages = new ArrayList<String>();
    }

    public GenericAPIResponse(List<String> messages, boolean success, List<String> errors) {
        this.messages = messages;
        this.success = success;
        this.errors = errors;
    }

    public boolean getSuccess() {
        if ((errors != null && errors.size() > 0)) {
            success = false;
        } else {
            success = true;
        }

        return success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        errors.add(error);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
