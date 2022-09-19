package me.java.designpattern.creation.abstractfactory.phone;

public interface CellPhoneFactory {

    CellPhone createCellPhone();

    CellPhone.Type getType();
}
