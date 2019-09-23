package com.manager.barcode.filter;

public interface BarcodeFilter
{
    void put(String barcode);
    Boolean mightContain(String barcode);
}