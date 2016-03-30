package com.epam.training.movie.theater.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class PDFPage extends AbstractView {

    public PDFPage() {
        this.setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter pdfWriter,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        List words = (List) model.get("events");
        for (int i = 0; i < words.size(); i++) {
            document.add(new Paragraph((String) words.get(i)));
        }
    }

    protected Document newDocument() {
        return new Document(PageSize.A4);
    }

    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }

    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request) throws DocumentException {
        writer.setViewerPreferences(this.getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return 2053;
    }

    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ByteArrayOutputStream baos = this.createTemporaryOutputStream();
        Document document = this.newDocument();
        PdfWriter writer = this.newWriter(document, baos);
        this.prepareWriter(model, writer, request);
        this.buildPdfMetadata(model, document, request);
        document.open();
        this.buildPdfDocument(model, document, writer, request, response);
        document.close();
        this.writeToResponse(response, baos);
    }
}