package org.example;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AttributeExtraction {

    private static StanfordCoreNLP pipeline;

    static {
        // Set properties for CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse");
        pipeline = new StanfordCoreNLP(props);
    }

    public static List<String> extractAttributes(String text) {
        List<String> attributes = new ArrayList<>();

        // Annotate the text
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        // Process each sentence
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {

            // Get named entities and dependency graph
            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);


            // Extract attribute-value pairs using dependency parsing
            for (SemanticGraphEdge edge : sentence.get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class).edgeIterable()) {
                String governorText = edge.getGovernor().originalText();
                IndexedWord dependentWord = edge.getGovernor();
                if (dependentWord != null) {
                    CoreMap governor = dependentWord.backingLabel();  // Convert to CoreMap

                }
                CoreMap governor = dependentWord != null ? dependentWord.backingLabel() : null;
                System.out.println("Governor Text " + governorText);
                System.out.println("Governor" + governor);

//                IndexedWord dependentWord = dependentEdge.getDependent();
                System.out.println("Edge dependent: " + edge.getDependent());
                IndexedWord dependentEdge = edge.getDependent();
                CoreMap dependent = null;
                if (dependentEdge != null) {
                    dependent = dependentEdge.backingLabel();
                }
                System.out.println("Dependent: " + dependent);


                String relation = edge.getRelation().getShortName();
                System.out.println(governorText + " " + relation);
                // Check for specific dependency patterns (modify as needed)
                if (relation.equals("nmod") && governor.get(CoreAnnotations.NamedEntityTagAnnotation.class) != null) {
                    System.out.println("equal");
                    String attribute = governor.get(CoreAnnotations.TextAnnotation.class);

                    String value =  dependent.get(CoreAnnotations.TextAnnotation.class);
//                    System.out.println(attribute);
//                    System.out.println(value);
//                    System.out.println(attributes);
                    for (CoreMap token : tokens) {
                        String nerTag = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//                        System.out.println(token.toString() + " -> " + nerTag);

                        if (!nerTag.equals("O")) {
                            attributes.add(attribute + " : " + token.get(CoreAnnotations.TextAnnotation.class) + value);
                        }
                    }
//                    attributes.add(attribute + " : " +  value);
                }
            }
        }

        return attributes;

    }

    public static void main(String[] args) {
        String text = "The car has the top speed of 150 mph.";
        List<String> attributes = extractAttributes(text);

        System.out.println("Extracted attributes:");
        for (String attribute : attributes) {
            System.out.println(attribute);
        }
    }
}
