package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessing {
    public static void main(String[] args) {
        String input = "Battery Capacity 35Ah\n\nwith special characters: *&^%$#@!";

        // Define the regex pattern to match newlines and special characters
        String regex = "[\\n\\r\\t\\p{Punct}]";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(input);

        // Replace all occurrences of the matched pattern with an empty string
        String result = matcher.replaceAll("");

        System.out.println("Original string: " + input);
        System.out.println("String after removing newlines and special characters: " + result);
    }
}
