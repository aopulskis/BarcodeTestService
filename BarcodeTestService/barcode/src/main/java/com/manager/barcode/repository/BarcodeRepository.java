package com.manager.barcode.repository;

import com.manager.barcode.models.Barcode;

public interface BarcodeRepository
{
    public java.util.Optional<Barcode> findById(int id);
    public java.util.Optional < Barcode > findByBarcode(String barcode);
    public int insertBarcode(Barcode barcode);
}