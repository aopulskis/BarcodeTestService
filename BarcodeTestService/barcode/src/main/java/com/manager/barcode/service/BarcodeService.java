package com.manager.barcode.service;

public interface BarcodeService {
    public Integer useBarcode(String barcode);
    public Boolean isBarcodeUsed(String barcode);
}
