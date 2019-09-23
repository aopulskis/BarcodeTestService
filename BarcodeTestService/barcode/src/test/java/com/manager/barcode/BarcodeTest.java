package com.manager.barcode;

import static org.junit.Assert.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import com.manager.barcode.models.Barcode;

import org.junit.Test;

public class BarcodeTest {

    @Test
	public void id_setted_getted() {
        Integer id = 10001;
        Barcode target = new Barcode();
        target.setId(id);

        assertFalse(target.getId() == 0);
        assertTrue(target.getId() == id);
    }
    
    @Test
	public void barcode_setted_getted() {
        String barcode = "ZST12345678901";
        Barcode target = new Barcode();
        target.setBarcode(barcode);

        assertThat(target.getBarcode()).isNotEqualTo(barcode.toLowerCase());
        assertThat(target.getBarcode()).isEqualTo(barcode);
	}
}