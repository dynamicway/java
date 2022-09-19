package me.java.designpattern.creation.abstractfactory.phone.apple;

import me.java.designpattern.creation.abstractfactory.phone.CellPhone;
import me.java.designpattern.creation.abstractfactory.phone.CellPhoneFactory;

public class AppleCellPhoneFactory implements CellPhoneFactory {
    @Override
    public CellPhone createCellPhone() {
        AppleCellPhoneCase appleCellPhoneCase = new AppleCellPhoneCase();
        AppleCellPhoneDevice appleCellPhoneDevice = new AppleCellPhoneDevice();
        return new CellPhone(
                appleCellPhoneCase,
                appleCellPhoneDevice,
                CellPhone.Type.APPLE
        );
    }

    @Override
    public CellPhone.Type getType() {
        return CellPhone.Type.APPLE;
    }
}
