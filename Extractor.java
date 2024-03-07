package org.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.awt.*;
import java.io.IOException;

public class Extractor {

//    private static int contentX;

    public static String extractor(PDDocument document, int columns, int contentX, int contentY, int contentWidth, int contentHeight) throws IOException {
//        Extractor.contentX = contentX;
//        int contentX = contentX;
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);



        // Extract from the first page
        StringBuilder contentText = new StringBuilder();
        // Access text for specific regions
        for(int i = 0; i < columns; i++){
            contentX += i * 300;
            Rectangle contentRegion = new Rectangle(contentX, contentY, contentWidth, contentHeight);
            stripper.addRegion("content" + i, contentRegion);
            stripper.extractRegions(document.getPage(1));
            String textForRegion = stripper.getTextForRegion("content" + i);
            if (textForRegion != null) {
                contentText.append(textForRegion);
            }
        }


//        document.close();
        return contentText.toString();
    }
}
