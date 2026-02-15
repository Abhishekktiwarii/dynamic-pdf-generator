package com.pdfgenerator.dynamic_pdf.service;

import com.pdfgenerator.dynamic_pdf.model.InvoiceRequest;

public interface InvoiceService {
    String generateInvoice(InvoiceRequest request);
}
