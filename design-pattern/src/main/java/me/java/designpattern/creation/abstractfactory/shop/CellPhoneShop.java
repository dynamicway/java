package me.java.designpattern.creation.abstractfactory.shop;

import me.java.designpattern.creation.abstractfactory.phone.CellPhone;
import me.java.designpattern.creation.abstractfactory.phone.CellPhoneProvider;

public class CellPhoneShop {

    private final CellPhoneProvider cellPhoneProvider;

    public CellPhoneShop(CellPhoneProvider cellPhoneProvider) {
        this.cellPhoneProvider = cellPhoneProvider;
    }

    public CellPhone order(CellPhone.Type cellPhoneType) {
        return cellPhoneProvider.createCellPhone(cellPhoneType);
    }

}
