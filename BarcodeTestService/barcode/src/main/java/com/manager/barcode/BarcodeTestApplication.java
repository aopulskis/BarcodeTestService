package com.manager.barcode;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BarcodeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarcodeTestApplication.class, args);
	}

	@Bean
	public BloomFilter<String> getBloomFilter()
    {
        return BloomFilter.create(
            Funnels.stringFunnel(Charsets.UTF_16),
            5000000,
            0.01);
    }
}
