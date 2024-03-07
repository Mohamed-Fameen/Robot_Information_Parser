package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractAll {

    public static void main(String[] args) throws IOException {
//        List<Integer> performance = new ArrayList<>();
        int[] performance = {0, 120, 300, 150, 2};
        int[] chasis = {0, 270, 300, 60, 2};
        int[] energySystem = {0, 370, 300, 60, 2};
        int[] safetySystem = {0, 470, 300, 50, 2};
        int[] automationInterface = {0, 540, 300, 80, 1};
        int[] conrolSystem = {300, 490, 300, 120, 1};
        int[] accessories = {0, 600, 300, 120, 1 };
        int[] environment = {300, 620, 300, 60, 1};

        HashMap<String, int[]> headers = new HashMap<>();

        headers.put("Performance", performance);
        headers.put("Chasis", chasis);
        headers.put("Energy System", energySystem);
        headers.put("Safety System", safetySystem);
        headers.put("Automation Interface", automationInterface);
        headers.put("Control System", conrolSystem);
        headers.put("Accessories", accessories);
        headers.put("Environment", environment);

        File pdfFile = new File("C:/Users/MohamedFameen/Downloads/OTTO-100-Data-Sheet.pdf");
        PDDocument document = Loader.loadPDF(pdfFile);
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        for(Map.Entry<String, int[]> set : headers.entrySet()){
            System.out.println(set.getKey() + " -> " + set.getValue()[0]);
            System.out.println(Extractor.extractor(document, set.getValue()[4], set.getValue()[0], set.getValue()[1], set.getValue()[2], set.getValue()[3]));

        }
        document.close();
    }
}
