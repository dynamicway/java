package me.java.designpattern.creation.abstractfactory.phone.samsung;

import me.java.designpattern.creation.abstractfactory.phone.CellPhone;
import me.java.designpattern.creation.abstractfactory.phone.CellPhoneFactory;

public class SamsungCellPhoneFactory implements CellPhoneFactory {

    @Override
    public CellPhone createCellPhone() {
        SamsungCellPhoneDevice samsungCellPhoneDevice = new SamsungCellPhoneDevice();
        SamsungCellPhoneCase samsungCellPhoneCase = new SamsungCellPhoneCase();
        return new CellPhone(samsungCellPhoneCase, samsungCellPhoneDevice, CellPhone.Type.SAMSUNG);
    }

    @Override
    public CellPhone.Type getType() {
        return CellPhone.Type.SAMSUNG;
    }

}
