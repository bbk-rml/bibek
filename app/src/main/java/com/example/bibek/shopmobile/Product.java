package com.example.bibek.shopmobile;

public class Product {

    public String barcode;
    public String productName;
    public String productDescription;
    public String productPrice;
    public String productQuantity;

    public Product()

    {

    }

    public Product(String barcode, String productName,String productDescription,String productPrice, String productQuantity)
    {
        this.barcode = barcode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;

    }
}
