package com.manager.barcode.models;

import java.io.Serializable;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "barcode")
//@XmlAccessorType(XmlAccessType.FIELD)
public final class Barcode  implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String barcode;

    public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }

        //@NotEmpty(message = "Barcode must not be empty.")
        //@Size(min = 13, max = 25, message = "Barcode length must be between 13 and 23.")
        //@Pattern
        public void setBarcode(String barcode) {
           this.barcode = barcode.trim().toUpperCase();
        }

        public String getBarcode() {
            return barcode;
        }
}