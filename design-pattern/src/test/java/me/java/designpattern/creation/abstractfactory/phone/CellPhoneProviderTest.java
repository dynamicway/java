package me.java.designpattern.creation.abstractfactory.phone;

import me.java.designpattern.creation.abstractfactory.phone.apple.AppleCellPhoneFactory;
import me.java.designpattern.creation.abstractfactory.phone.google.GoogleCellPhoneFactory;
import me.java.designpattern.creation.abstractfactory.phone.samsung.SamsungCellPhoneFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CellPhoneProviderTest {

    @ParameterizedTest
    @EnumSource(CellPhone.Type.class)
    void the_cell_phone_type_is_the_same_as_requested(CellPhone.Type cellPhoneType) {
        CellPhoneProvider cellPhoneProvider = new CellPhoneProvider(List.of(new SamsungCellPhoneFactory(), new AppleCellPhoneFactory(), new GoogleCellPhoneFactory()));

        CellPhone cellPhone = cellPhoneProvider.createCellPhone(cellPhoneType);

        assertThat(cellPhone.cellPhoneType()).isEqualTo(cellPhoneType);
    }

    @Test
    void cannot_create_a_cell_phone_that_not_exists_a_type_of_cell_phone_factory() {
        CellPhoneProvider cellPhoneProviderWithOnlySamsungFactory = new CellPhoneProvider(List.of(new SamsungCellPhoneFactory()));

        assertThatThrownBy(() -> cellPhoneProviderWithOnlySamsungFactory.createCellPhone(CellPhone.Type.APPLE))
                .isInstanceOf(IllegalStateException.class);
    }

}
