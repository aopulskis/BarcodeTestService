package com.manager.barcode.controller;

import com.manager.barcode.models.Barcode;
import com.manager.barcode.service.BarcodeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarcodeTestController
{
    private final BarcodeService barcodeService;

    public BarcodeTestController(final BarcodeService barcodeService)
    {
        this.barcodeService = barcodeService;     
    }

    @PostMapping("/usebarcode")
    public ResponseEntity<String> useBarcode(@RequestBody Barcode barcode) {

        Integer barcodeId = barcodeService.useBarcode(barcode.getBarcode());
        if (barcodeId <= 0)
        {
            String barcodeValue = barcode.getBarcode();
            if(barcodeValue == null || barcodeValue.isEmpty())
            { 
                barcodeValue ="The barcode was not set";
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "The action 'useBarcode' with " + barcodeValue + "' barcode failed. The barcode was used or the barcode doesn't fulfill on of requirements: length must be between 13 and 15, no space symbols are allowed, expected upper case.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(barcodeId.toString());
    }

    @GetMapping("/isBarcodeUsed/{barcode}")
    public boolean isBarcodeUsed(@PathVariable String barcode) {
        return barcodeService.isBarcodeUsed(barcode);
    }
}