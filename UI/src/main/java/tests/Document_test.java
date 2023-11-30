package tests;
import Backend.Document;
import Backend.Word_Object;
import Backend.Doc_Error;

public class Document_test {

    private static Document document;

    public static void main(String[] args) {
        setUp();
        
        testMarkCapitals();
        testRunSpellCheck();
        testCheckSingleWord();
        testGetWordInLinkedList();
        testUpdateDocAnalysis();
        testGetDocAnalysis();
        testGetDocErrorValues();
        testGetDocError();
        testAddToUserDict();
        testDecreaseCurrentMisspeltWords();
        testDecreaseCurrentDoubleWords();
        testDecreaseCurrentCapitalErrors();
    }

    static void setUp() {
        document = new Document("Initial text");
    }

    

    static void testMarkCapitals() {
        Word_Object word = new Word_Object("Test");
        document.mark_capitals(word);
        System.out.println("testMarkCapitals executed");
    }

    static void testRunSpellCheck() {
        document.run_spell_check();
        System.out.println("testRunSpellCheck executed");
    }

    static void testCheckSingleWord() {
        Word_Object word = new Word_Object("testing");
        Word_Object result = document.check_single_word(word);
        System.out.println(result != null ? "testCheckSingleWord passed" : "testCheckSingleWord failed");
    }

    static void testGetWordInLinkedList() {
        Word_Object word = document.get_word_in_linked_list(0);
        System.out.println(word != null ? "testGetWordInLinkedList passed" : "testGetWordInLinkedList failed");
    }

    static void testUpdateDocAnalysis() {
        document.update_doc_analysis();
        System.out.println("testUpdateDocAnalysis executed");
    }

    static void testGetDocAnalysis() {
        int[] analysis = document.get_doc_analysis(5);
        System.out.println(analysis != null && analysis.length == 3 ? "testGetDocAnalysis passed" : "testGetDocAnalysis failed");
    }

    static void testGetDocErrorValues() {
        int[] errors = document.get_doc_error_values();
        System.out.println(errors != null && errors.length == 6 ? "testGetDocErrorValues passed" : "testGetDocErrorValues failed");
    }

    static void testGetDocError() {
        Doc_Error error = document.get_doc_error();
        System.out.println(error != null ? "testGetDocError passed" : "testGetDocError failed");
    }

    static void testAddToUserDict() {
        document.add_to_user_dict("newword");
        System.out.println("testAddToUserDict executed");
    }

    static void testDecreaseCurrentMisspeltWords() {
        document.decrease_current_misspelt_words();
        System.out.println("testDecreaseCurrentMisspeltWords executed");
    }

    static void testDecreaseCurrentDoubleWords() {
        document.decrease_current_double_words();
        System.out.println("testDecreaseCurrentDoubleWords executed");
    }

    static void testDecreaseCurrentCapitalErrors() {
        document.decrease_current_capital_errors();
        System.out.println("testDecreaseCurrentCapitalErrors executed");
    }
}
