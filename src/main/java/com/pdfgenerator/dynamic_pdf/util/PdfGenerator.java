package com.pdfgenerator.dynamic_pdf.util;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class PdfGenerator {

    public static void generatePdfFromHtml(String html, String outputPath) {
        try (OutputStream os = new FileOutputStream(outputPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
