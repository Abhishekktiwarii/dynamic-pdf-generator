package com.pdfgenerator.dynamic_pdf.controller;

import com.pdfgenerator.dynamic_pdf.model.InvoiceRequest;
import com.pdfgenerator.dynamic_pdf.service.InvoiceService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.File;

@RestController
@RequestMapping("/api/pdf")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generatePdf(
            @RequestBody InvoiceRequest request) throws Exception {

        String filePath = invoiceService.generateInvoice(request);

        File file = new File(filePath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new FileInputStream(file)));
    }
}
