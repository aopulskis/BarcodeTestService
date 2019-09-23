package com.manager.barcode;

import com.manager.barcode.models.Barcode;
import com.manager.barcode.repository.BarcodeRowMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BarcodeRowMapperTest {

    @Mock
    private ResultSet rs;

    Barcode barcode = null;

    @Before
    public void setUp() throws Exception {
        barcode = new Barcode();
        barcode.setId(100);
        barcode.setBarcode("ASD123456789012");

        when(rs.getInt("id")).thenReturn(barcode.getId());
        when(rs.getString("barcode")).thenReturn(barcode.getBarcode());
    }

    @Test
    public void mappingTest()  throws SQLException
    {
        BarcodeRowMapper target = new BarcodeRowMapper();
        Barcode parsedBarcode = target.mapRow(rs, 1);

        assertTrue(parsedBarcode.getId() == this.barcode.getId());
        assertThat(parsedBarcode.getBarcode()).isEqualTo(barcode.getBarcode());
    }
}