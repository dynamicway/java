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
        CellPhoneFactory cellPhoneFactory = getCellPhoneFactory(cellPhoneType);
        verifyFactoryIsNull(cellPhoneFactory);
        return cellPhoneFactory.createCellPhone();
    }

    private CellPhoneFactory getCellPhoneFactory(CellPhone.Type cellPhoneType) {
        return cellPhoneFactories.get(cellPhoneType);
    }

    private void verifyFactoryIsNull(CellPhoneFactory cellPhoneFactory) {
        if (cellPhoneFactory == null)
            throw new IllegalStateException();
    }
}
