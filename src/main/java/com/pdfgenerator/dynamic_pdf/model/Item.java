package com.pdfgenerator.dynamic_pdf.model;

import lombok.Data;

@Data
public class Item {
    private String name;
    private String quantity;
    private Double rate;
    private Double amount;
}
