package com.manager.barcode.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.manager.barcode.models.Barcode;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BarcodeRowMapper implements RowMapper < Barcode > {
    @Override
    public Barcode mapRow(ResultSet rs, int rowNum) throws SQLException {
        Barcode barcode = new Barcode();
        barcode.setId(rs.getInt("id"));
        barcode.setBarcode(rs.getString("barcode"));
        return barcode;
    }
}