package com.pdfgenerator.dynamic_pdf.service.impl;

import com.pdfgenerator.dynamic_pdf.model.InvoiceRequest;
import com.pdfgenerator.dynamic_pdf.service.InvoiceService;
import com.pdfgenerator.dynamic_pdf.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final SpringTemplateEngine templateEngine;

    @Value("${pdf.storage.path}")
    private String storagePath;

    public InvoiceServiceImpl(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String generateInvoice(InvoiceRequest request) {
        try {

            new File(storagePath).mkdirs();

            String fileName = generateHash(request) + ".pdf";
            String filePath = storagePath + "/" + fileName;

            File pdfFile = new File(filePath);

            if (pdfFile.exists()) {
                return filePath;
            }

            Context context = new Context();
            context.setVariable("seller", request.getSeller());
            context.setVariable("sellerGstin", request.getSellerGstin());
            context.setVariable("sellerAddress", request.getSellerAddress());
            context.setVariable("buyer", request.getBuyer());
            context.setVariable("buyerGstin", request.getBuyerGstin());
            context.setVariable("buyerAddress", request.getBuyerAddress());
            context.setVariable("items", request.getItems());

            String html = templateEngine.process("invoice", context);

            PdfGenerator.generatePdfFromHtml(html, filePath);

            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("Error generating invoice", e);
        }
    }

    private String generateHash(InvoiceRequest request) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(request.toString().getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
