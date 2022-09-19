package me.java.designpattern.creation.abstractfactory.phone;

import java.util.EnumMap;
import java.util.List;

public class CellPhoneProvider {

    private final EnumMap<CellPhone.Type, CellPhoneFactory> cellPhoneFactories;

    public CellPhoneProvider(List<CellPhoneFactory> cellPhoneFactories) {
        this.cellPhoneFactories = new EnumMap<>(CellPhone.Type.class);
        for (CellPhoneFactory cellPhoneFactory : cellPhoneFactories) {
            this.cellPhoneFactories.put(cellPhoneFactory.getType(), cellPhoneFactory);
        }
    }

    public CellPhone createCellPhone(CellPhone.Type cellPhoneType) {
        CellPhoneFactory cellPhoneFactory = cellPhoneFactories.get(cellPhoneType);
        return cellPhoneFactory.createCellPhone();
    }
}
