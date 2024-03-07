package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class PerformanceExtractor {
    public static void main(String[] args) throws IOException {
        File pdfFile = new File("C:/Users/MohamedFameen/Downloads/OTTO-100-Data-Sheet.pdf");
        PDDocument document = Loader.loadPDF(pdfFile);
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        int contentX = 0;
        int contentY = 120;
        int contentWidth = 300;
        int contentHeight = 700;
        int columns = 2;
//        Rectangle contentRegion = new Rectangle(contentX, contentY, contentWidth, contentHeight);
//        stripper.addRegion("content", contentRegion);
//
//        stripper.extractRegions(document.getPage(1)); // Extract from the first page
//
//        // Access text for specific regions
//        String contentText = stripper.getTextForRegion("content");
//

//        System.out.println("\nContent Text:\n" + contentText);
//
        System.out.println(Extractor.extractor(document, columns, contentX, contentY,contentWidth,contentHeight));
        document.close();
    }
}
