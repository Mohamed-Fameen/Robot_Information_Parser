package org.example;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AttributeExtraction1 {

    public static void main(String[] args) {
        // Set properties for CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Input text
        String text = "The red car has a top speed of 150 mph and a mileage of 30 mpg. It also has a spacious interior and comfortable seats.";

        // Annotate the text
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        // Process each sentence
        List<String> attributes = new ArrayList<>();
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            // Process dependency parse tree of the sentence
            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
            for (int i = 0; i < tokens.size(); i++) {
                CoreMap token = tokens.get(i);
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                // Check if the word is an attribute
                if (isAttribute(word, ne)) {
                    // Check the next token for value
                    if (i + 1 < tokens.size()) {
                        CoreMap nextToken = tokens.get(i + 1);
                        String value = nextToken.get(CoreAnnotations.TextAnnotation.class);
                        attributes.add("Attribute: " + word + ", Value: " + value);
                    }
                }
            }
        }

        // Output
        System.out.println("Output:");
        for (String attribute : attributes) {
            System.out.println(attribute);
        }
    }

    // Method to determine if a word is an attribute
    private static boolean isAttribute(String word, String ne) {
        // Define attribute words
        String[] attributeWords = {"speed", "mileage", "color", "interior", "seat"};
        for (String attribute : attributeWords) {
            if (word.equalsIgnoreCase(attribute)) {
                return true;
            }
        }
        return false;
    }
}
