package me.java.designpattern.creation.abstractfactory.phone;

import me.java.designpattern.creation.abstractfactory.phone.samsung.SamsungCellPhoneFactory;

import java.util.EnumMap;

public class CellPhoneProvider {

    private final EnumMap<CellPhone.Type, CellPhoneFactory> cellPhoneFactories;

    public CellPhoneProvider() {
        this.cellPhoneFactories = new EnumMap<>(CellPhone.Type.class);
        cellPhoneFactories.put(CellPhone.Type.SAMSUNG, new SamsungCellPhoneFactory());
    }

    public CellPhone createCellPhone(CellPhone.Type cellPhoneType) {
        CellPhoneFactory cellPhoneFactory = cellPhoneFactories.get(cellPhoneType);
        return cellPhoneFactory.createCellPhone();
    }
}
