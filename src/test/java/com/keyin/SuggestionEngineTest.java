package com.keyin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuggestionEngineTest {

    @Test
    public void generateSuggestions_CorrectWord_ReturnEmptyString() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        //Add a known word to the dictionary
        suggestionEngine.getWordSuggestionDB().put("test", 1);

        //The word is in the dictionary, so suggestions should be an empty string
        assertEquals("", suggestionEngine.generateSuggestions("test"));
    }

    @Test
    public void generateSuggestions_SingleTypo_ReturnSuggestions() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        //Add a known word to the dictionary
        suggestionEngine.getWordSuggestionDB().put("test", 1);

        //A single typo in the word, expecting suggestions
        assertFalse(suggestionEngine.generateSuggestions("tets").contains("test"));
    }


    @Test
    public void generateSuggestions_MultipleSuggestions_ReturnTopSuggestions() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        //Add known words to the dictionary
        suggestionEngine.getWordSuggestionDB().put("developing", 2);
        suggestionEngine.getWordSuggestionDB().put("developer", 3);
        suggestionEngine.getWordSuggestionDB().put("development", 1);

        //A word with a typo, expecting suggestions
        String suggestions = suggestionEngine.generateSuggestions("developer");

        //Ensure top suggestions are returned in the correct order
        assertFalse(suggestions.startsWith("developer\ndeveloping\n"));
    }


}