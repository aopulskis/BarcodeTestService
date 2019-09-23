package com.manager.barcode;

import com.manager.barcode.filter.BarcodeBloomFilter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BarcodeBloomFilterTest {

    private BarcodeBloomFilter target;
    private BloomFilter<String> bloomFilter;

    @Before
    public void initBarcodeBloomFilterCase() {

        this.bloomFilter = BloomFilter.create(
            Funnels.stringFunnel(Charsets.UTF_16),
            1000,
            0.01);;

        target = new BarcodeBloomFilter(bloomFilter);
    }

	@Test
	public void put_addedDataToFilter_dataFound() {
        String barcode = "ASD1234567890123";

        assertFalse(bloomFilter.mightContain(barcode));
        
        target.put(barcode);

        assertTrue(bloomFilter.mightContain(barcode));
	}

    @Test
	public void mightContain_addedDataToFilter_dataFound() {
        String barcode = "ASD1234567890123";

        assertFalse(bloomFilter.mightContain(barcode));
        
        target.put(barcode);

        assertTrue(target.mightContain(barcode));
	}

}
