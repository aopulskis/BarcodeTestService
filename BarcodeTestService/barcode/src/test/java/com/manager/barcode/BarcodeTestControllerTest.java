package com.manager.barcode;

//import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.manager.barcode.models.Barcode;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BarcodeTestControllerTest {

    @Test
	public void contextLoads() {
	}

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void isBarcodeUsedDataTest_ex() throws Exception {
        Barcode barcode = new Barcode();        
        barcode.setBarcode("123421");

        assertThat("false").isEqualTo(checkBarcode(barcode.getBarcode()).getBody());
        
        HttpEntity<Barcode> requestEntity = new HttpEntity<Barcode>(barcode, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/usebarcode"), 
          HttpMethod.POST, 
          requestEntity, 
          String.class);

        String responseBarcodeRecordId = response.getBody();

        assertThat("false").isEqualTo(checkBarcode(barcode.getBarcode()).getBody());
        assertTrue(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
        assertFalse(BarcodeTestControllerTest.isNumeric(responseBarcodeRecordId));
        
    }

    public static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    @Test
    public void useBarcodeTest() throws Exception {
        Barcode barcode = new Barcode();        
        barcode.setBarcode("TEST1234567890");

        assertThat("false").isEqualTo(checkBarcode(barcode.getBarcode()).getBody());
        
        HttpEntity<Barcode> requestEntity = new HttpEntity<Barcode>(barcode, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/usebarcode"), 
          HttpMethod.POST, 
          requestEntity, 
          String.class);

        String responseBarcodeRecordId = response.getBody();
        Integer barcodeRecordId = Integer.decode(responseBarcodeRecordId);

        assertTrue(response.getStatusCode() == HttpStatus.OK);
        assertTrue(barcodeRecordId>0);
        assertThat("true").isEqualTo(checkBarcode(barcode.getBarcode()).getBody());
    }

    private ResponseEntity<String> checkBarcode(String barcode)
    {
        HttpEntity<Boolean> entity = new HttpEntity<Boolean>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/isBarcodeUsed/"+barcode), HttpMethod.GET, entity, String.class);
        return response;
    }

    @Test
    public void isBarcodeUsedTest() throws Exception {
        String barcode = "TEST1234567890";
        HttpEntity<Boolean> entity = new HttpEntity<Boolean>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/isBarcodeUsed/"+barcode), HttpMethod.GET, entity, String.class);

        String expected = "false";
        assertThat(expected).isEqualTo(response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}