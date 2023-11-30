package tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Backend.Document;
import Backend.Word_Object;
import Backend.Doc_Error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Backend.Document;
import Backend.Word_Object;
import Backend.Doc_Error;

class DocumentTest {

    private Document document;

    // Set up a document object for testing
    @BeforeEach
    void setUp() {
        document = new Document("Initial text");
    }

    @Test
    void testConstructor() {
        assertNotNull(document);
        assertEquals("Initial text", document.text);
        // Further assertions to verify initial state of the document
    }

    @Test
    void testPopulateLinkedList() {
        document.populateLinkedList("New text for testing");
        // Assertions to verify the linked list is populated correctly
    }

    @Test
    void testClearUserDict() {
        document.clear_user_dict();
        // Assertions to verify user dictionary is cleared
    }

    @Test
    void testUserDictIsNull() {
        assertFalse(document.user_dict_is_null());
        // Additional scenarios for testing
    }

    @Test
    void testGetUserDictFormatted() {
        String result = document.get_user_dict_formatted();
        assertNotNull(result);
        // Additional assertions based on expected format
    }

    @Test
    void testMarkCapitals() {
        Word_Object word = new Word_Object("Test");
        document.mark_capitals(word);
        // Assertions to verify capitals are marked correctly
    }

    @Test
    void testRunSpellCheck() {
        document.run_spell_check();
        // Assertions to verify spell check functionality
    }

    @Test
    void testCheckSingleWord() {
        Word_Object word = new Word_Object("testing");
        Word_Object result = document.check_single_word(word);
        assertNotNull(result);
        // Additional assertions for spell check results
    }

    @Test
    void testGetWordInLinkedList() {
        Word_Object word = document.get_word_in_linked_list(0);
        assertNotNull(word);
        // Assertions to verify correct retrieval of word
    }

    @Test
    void testUpdateDocAnalysis() {
        document.update_doc_analysis();
        // Assertions to verify document analysis is updated correctly
    }

    @Test
    void testGetDocAnalysis() {
        int[] analysis = document.get_doc_analysis(5);
        assertNotNull(analysis);
        assertTrue(analysis.length == 3);
        // Further assertions based on expected analysis data
    }

    @Test
    void testGetDocErrorValues() {
        int[] errors = document.get_doc_error_values();
        assertNotNull(errors);
        assertTrue(errors.length == 6);
        // Assertions based on expected error data
    }

    @Test
    void testGetDocError() {
        Doc_Error error = document.get_doc_error();
        assertNotNull(error);
        // Further validations as necessary
    }

    @Test
    void testAddToUserDict() {
        document.add_to_user_dict("newword");
        // Assertions to verify word is added to user dictionary
    }

    @Test
    void testDecreaseCurrentMisspeltWords() {
        document.decrease_current_misspelt_words();
        // Assertions to verify count is decreased
    }

    @Test
    void testDecreaseCurrentDoubleWords() {
        document.decrease_current_double_words();
        // Assertions to verify count is decreased
    }

    @Test
    void testDecreaseCurrentCapitalErrors() {
        document.decrease_current_capital_errors();
        // Assertions to verify count is decreased
    }

    // Additional tests can be added as needed to cover more scenarios and edge cases
}




