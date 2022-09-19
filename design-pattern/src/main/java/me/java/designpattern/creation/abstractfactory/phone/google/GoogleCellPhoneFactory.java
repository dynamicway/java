package me.java.designpattern.creation.abstractfactory.phone.google;

import me.java.designpattern.creation.abstractfactory.phone.CellPhone;
import me.java.designpattern.creation.abstractfactory.phone.CellPhoneFactory;

public class GoogleCellPhoneFactory implements CellPhoneFactory {
    @Override
    public CellPhone createCellPhone() {
        GoogleCellPhoneCase googleCellPhoneCase = new GoogleCellPhoneCase();
        GoogleCellPhoneDevice googleCellPhoneDevice = new GoogleCellPhoneDevice();
        return new CellPhone(
                googleCellPhoneCase,
                googleCellPhoneDevice,
                CellPhone.Type.GOOGLE
        );
    }

    @Override
    public CellPhone.Type getType() {
        return CellPhone.Type.GOOGLE;
    }
}
