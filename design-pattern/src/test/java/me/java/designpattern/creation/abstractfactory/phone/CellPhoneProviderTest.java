package me.java.designpattern.creation.abstractfactory.phone;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class CellPhoneProviderTest {

    @ParameterizedTest
    @EnumSource(CellPhone.Type.class)
    void the_cell_phone_type_is_the_same_as_requested(CellPhone.Type cellPhoneType) {
        CellPhoneProvider cellPhoneProvider = new CellPhoneProvider();

        CellPhone cellPhone = cellPhoneProvider.createCellPhone(cellPhoneType);

        assertThat(cellPhone.cellPhoneType()).isEqualTo(cellPhoneType);
    }

}
