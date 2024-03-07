package org.example;
//"Extracts the text from the pdf using Apache PDFBox with discrepancies"
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PdfBoxParser {
    public static void main(String[] args) throws IOException {
        File file = new File("C:/Users/MohamedFameen/Downloads/OTTO-100-Data-Sheet.pdf");

        PDDocument document = Loader.loadPDF(file);
        PDDocumentInformation pdd = document.getDocumentInformation();
        PDFTextStripperByArea pdfStripper = new PDFTextStripperByArea();



        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        // Define rectangular region (in points) to extract text from

        for(int y = 120; y <= 600; y+= 120) {
            Rectangle rect = new Rectangle(0, y, 600, 160);
            stripper.addRegion("myRegion", rect);

            // Extract text from the specified region
            PDPage firstPage = document.getPage(1);
            stripper.extractRegions(firstPage);

            // Get text from the specified region
            String regionText = stripper.getTextForRegion("myRegion");

            // Print text from the specified region
            System.out.println("Text from the specified region:");
            System.out.println(regionText);
        }


        document.close();
    }
}

