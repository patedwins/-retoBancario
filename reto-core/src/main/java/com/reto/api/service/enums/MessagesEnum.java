package com.reto.api.service.enums;

/**
 * Messages enumerator to error manage.
 *
 * @author patedwins
 * @version 1.0.0
 */
public enum MessagesEnum {

    ERROR_404("error.message.404");

    private String value;

    MessagesEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
