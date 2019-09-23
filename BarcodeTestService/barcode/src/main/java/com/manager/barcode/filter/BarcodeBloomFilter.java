package com.manager.barcode.filter;

//import com.google.common.base.Charsets;
//import com.google.common.hash.Funnels;

import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;

@Component
public final class BarcodeBloomFilter implements BarcodeFilter
{
    private final BloomFilter<String> filter;

    /*public BarcodeBloomFilter()
    {
        this.filter = BloomFilter.create(
            Funnels.stringFunnel(Charsets.UTF_16),
            5000000,
            0.01);
    }*/

    public BarcodeBloomFilter(final BloomFilter<String> filter)
    {
        this.filter = filter;
    }

    @Override
    public void put(String barcode) {
        filter.put(barcode);
    }

    @Override
    public Boolean mightContain(String barcode) {
        return filter.mightContain(barcode);
    }

}