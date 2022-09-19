package me.java.designpattern.creation.abstractfactory.phone;

public record CellPhone(CellPhoneCase cellPhoneCase, CellPhoneDevice cellPhoneDevice, Type cellPhoneType) {

    public enum Type {
        SAMSUNG,
        GOOGLE,
        APPLE
    }

}
