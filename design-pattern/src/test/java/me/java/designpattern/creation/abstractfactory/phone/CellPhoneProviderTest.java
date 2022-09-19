package me.java.designpattern.creation.abstractfactory.phone;

import me.java.designpattern.creation.abstractfactory.phone.apple.AppleCellPhoneFactory;
import me.java.designpattern.creation.abstractfactory.phone.samsung.SamsungCellPhoneFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CellPhoneProviderTest {

    @ParameterizedTest
    @EnumSource(CellPhone.Type.class)
    void the_cell_phone_type_is_the_same_as_requested(CellPhone.Type cellPhoneType) {
        CellPhoneProvider cellPhoneProvider = new CellPhoneProvider(List.of(new SamsungCellPhoneFactory(), new AppleCellPhoneFactory()));

        CellPhone cellPhone = cellPhoneProvider.createCellPhone(cellPhoneType);

        assertThat(cellPhone.cellPhoneType()).isEqualTo(cellPhoneType);
    }

}
