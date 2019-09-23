package com.manager.barcode.service;

import com.manager.barcode.repository.BarcodeRepository;
import com.manager.barcode.filter.BarcodeFilter;
import com.manager.barcode.models.Barcode;

import org.springframework.stereotype.Service;

@Service
public class DefaultBarcodeService implements BarcodeService
{
    private final BarcodeRepository barcodeRepository;
    private final BarcodeFilter barcodeFilter;

    public DefaultBarcodeService(final BarcodeRepository barcodeRepository, final BarcodeFilter barcodeFilter)
    {
        this.barcodeRepository = barcodeRepository;
        this.barcodeFilter = barcodeFilter;
    }

    @Override
    public Integer useBarcode(String barcode) {
        int barcodeId = -1;

        if (!isBarcodeUsed(barcode))
        {
            Barcode barcodeEntity = new Barcode();
            barcodeEntity.setBarcode(barcode);
            barcodeId = barcodeRepository.insertBarcode(barcodeEntity);
            if (barcodeId!= -1)
            {
                barcodeFilter.put(barcode);
            }
        }
        return barcodeId;
    }

    @Override
    public Boolean isBarcodeUsed(String barcode) {
        Boolean barcodeFilterMightContain = barcodeFilter.mightContain(barcode);
        return barcodeFilterMightContain;
    }
   
}