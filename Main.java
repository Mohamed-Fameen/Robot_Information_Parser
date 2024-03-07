package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;
import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
//import org.apache.tika.parser.pdf.PDFParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws IOException, TesseractException, TikaException, SAXException {
        File file = new File("C:/Users/MohamedFameen/Downloads/OTTO-100-Data-Sheet.pdf");

        PDDocument document = Loader.loadPDF(file);
        PDDocumentInformation pdd = document.getDocumentInformation();
        PDFTextStripperByArea pdfStripper = new PDFTextStripperByArea();
//
//        // Extract text from the PDF
        String text = pdfStripper.getText(document);

//         Display extracted text
//        System.out.println("Extracted Text:");
//        System.out.println(text);

        // Close the document
//        document.close();
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

//
//
//        // Close the document
//        BufferedImage image = ImageIO.read(new File("C:/Users/MohamedFameen/Documents/Robot_Information_Parser/OTTOPG22.png"));
//
//        // Create an OCR object.
//        Tesseract ocr = new Tesseract();
//
//        // Set the language of the text to be recognized.
//        ocr.setLanguage("eng");
//
//        // Call the OCR object's recognizeText() method to extract the text from the image.
//        String text = ocr.doOCR(image);
//        Parser parser = new AutoDetectParser();
//
//        // Creating handler for parsed content
//        BodyContentHandler handler = new BodyContentHandler();
//
//        // Metadata for storing parsing metadata
//        Metadata metadata = new Metadata();
//
//        // Parsing the PDF file
//        try (InputStream stream = new FileInputStream(file)) {
//            parser.parse(stream, handler, metadata, new ParseContext());
//        }
//        catch (IOException | SAXException | TikaException e) {
//            e.printStackTrace();
//
//        }
//
//        // Printing parsed content
//        System.out.println("Parsed content:");
//        System.out.println(handler.toString());
//
//        // Printing metadata
//        System.out.println("Metadata:");
//        for (String name : metadata.names()) {
//            System.out.println(name + ": " + metadata.get(name));
//        }
//        document.close();
    }
        // Print the extracted text to the console.
//        System.out.println(text);


    }
