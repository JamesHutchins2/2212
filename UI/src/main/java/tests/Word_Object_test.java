package UI.src.main.java.tests;
import Backend.Word_Object;

package Backend;

public class WordObjectTest {

    public static void main(String[] args) {
        testWordObjectConstructor();
        testSettersAndGetters();
        testCapitalMarkers();
        testWordObjectFullConstructor();
        testSetStartIndex();
        testSetEndIndex();
        testGetStartIndex();
        testGetEndIndex();
        testGetPrevNode();
        testSetPrevNode();
        testGetNextNode();
        testSetNextNode();
        testGetWord();
        testSetEndsWithPunctuation();
        testGetEndsWithPunctuation();
        testSetIsFirstWord();
        testIsIsFirstWord();
        testSetWordAndUpdateProperties();
        testReplaceWord();
        testSetCapitalAt();
        testSetCapitalMarkets();
        testEndWithPeriod();
        testIsRealWord();
        testNeedsCapital();
        testNeedsPeriod();
        testSetSpacesAfter();
        testSetSpacesBefore();
        testIsDoubleWordAfter();
        testIsDoubleWordBefore();
        testSetSuggestions();
        testGetSuggestions();
        testGetIsCapitalAt();
        testHasNext();


    }

    private static void testWordObjectConstructor() {
        Word_Object wordObject = new Word_Object("Test");
        // Add assertions or print statements to check if the initial state is correct
        System.out.println("testWordObjectConstructor success");
    }

    private static void testSettersAndGetters() {
        Word_Object wordObject = new Word_Object("Test");
        // Add code to set values using setters and check them using getters
        System.out.println("testSettersAndGetters success");
    }

    private static void testCapitalMarkers() {
        Word_Object wordObject = new Word_Object("Test");
        // Add code to set up conditions for testing capital markers
        wordObject.set_capital_markets();
        // Add assertions or print statements to check if capital markers are set correctly
        System.out.println("testCapitalMarkers success");
    }

        private static void testReplaceWord() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.replace_word("NewTest");
        // Add assertions or print statements to check if word replacement is successful
        System.out.println("testReplaceWord success");
    }

    private static void testLinkedListOperations() {
        Word_Object wordObject1 = new Word_Object("Test1");
        Word_Object wordObject2 = new Word_Object("Test2");
        wordObject1.setNext_node(wordObject2);
        // Add assertions or print statements to check linked list operations
        assert wordObject1.hasNext();
        System.out.println("testLinkedListOperations success");
    }

    private static void testWordObjectFullConstructor() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.Word_Object_full("NewTest", true, true, true, true, true, false, false, "suggestion1", "suggestion2", "suggestion3");
        // Add assertions or print statements to check if full constructor sets values correctly
        assert wordObject.getWord().equals("NewTest");
        assert wordObject.isEnd_with_period();
        assert wordObject.isIs_real_word();
        assert wordObject.isNeeds_capital();
        assert wordObject.isNeeds_period();
        assert !wordObject.isIs_double_word_after();
        assert !wordObject.isIs_double_word_before();
        assert wordObject.getSuggestions()[0].equals("suggestion1");
        assert wordObject.getSuggestions()[1].equals("suggestion2");
        assert wordObject.getSuggestions()[2].equals("suggestion3");
        System.out.println("testWordObjectFullConstructor success");
    }

    private static void testWordObjectIndexMethods() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setStart_index(1);
        wordObject.setEnd_index(5);
        // Add assertions or print statements to check index-related methods
        assert wordObject.getStart_index() == 1;
        assert wordObject.getEnd_index() == 5;
        System.out.println("testWordObjectIndexMethods success");
    }

    private static void testWordObjectPunctuationMethods() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setEndsWithPunctuation(true);
        wordObject.setIs_first_word(true);
        // Add assertions or print statements to check punctuation-related methods
        assert wordObject.getEndsWithPunctuation();
        assert wordObject.isIs_first_word();
        System.out.println("testWordObjectPunctuationMethods success");
    }

    private static void testWordObjectSuggestionsMethods() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setSuggestions(new String[]{"suggestion1", "suggestion2", "suggestion3"});
        // Add assertions or print statements to check suggestions-related methods
        assert wordObject.getSuggestions()[0].equals("suggestion1");
        assert wordObject.getSuggestions()[1].equals("suggestion2");
        assert wordObject.getSuggestions()[2].equals("suggestion3");
        System.out.println("testWordObjectSuggestionsMethods success");
    }


    private static void testNeedsFirstCapital() {
        Word_Object wordObject = new Word_Object("Test");
        assert !wordObject.isNeeds_first_capital();
        System.out.println("testNeedsFirstCapital success");
    }

    private static void testSetNeedsFirstCapital() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setNeeds_first_capital(true);
        assert wordObject.isNeeds_first_capital();
        System.out.println("testSetNeedsFirstCapital success");
    }

    private static void testNeedsLowerButFirst() {
        Word_Object wordObject = new Word_Object("Test");
        assert !wordObject.isNeeds_lower_but_first();
        System.out.println("testNeedsLowerButFirst success");
    }

    private static void testSetNeedsLowerButFirst() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setNeeds_lower_but_first(true);
        assert wordObject.isNeeds_lower_but_first();
        System.out.println("testSetNeedsLowerButFirst success");
    }

    private static void testNeedsLower() {
        Word_Object wordObject = new Word_Object("Test");
        assert !wordObject.isNeeds_lower();
        System.out.println("testNeedsLower success");
    }

    private static void testSetNeedsLower() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setNeeds_lower(true);
        assert wordObject.isNeeds_lower();
        System.out.println("testSetNeedsLower success");
    }

    private static void testIsModified() {
        Word_Object wordObject = new Word_Object("Test");
        assert wordObject.isModified();
        System.out.println("testIsModified success");
    }

    private static void testSetModified() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setModified(false);
        assert !wordObject.isModified();
        System.out.println("testSetModified success");
    }


private static void testWordObjectFullConstructor() {
    Word_Object wordObject = new Word_Object("Test");
    wordObject.Word_Object_full("NewTest", true, true, true, true, true, false, false, "suggestion1", "suggestion2", "suggestion3");
    assert wordObject.getWord().equals("NewTest");
    assert wordObject.isEnd_with_period();
    assert wordObject.isIs_real_word();
    assert wordObject.isNeeds_capital();
    assert wordObject.isNeeds_period();
    assert !wordObject.isIs_double_word_after();
    assert !wordObject.isIs_double_word_before();
    assert wordObject.getSuggestions()[0].equals("suggestion1");
    assert wordObject.getSuggestions()[1].equals("suggestion2");
    assert wordObject.getSuggestions()[2].equals("suggestion3");
    System.out.println("testWordObjectFullConstructor success");
}

private static void testSetStartIndex() {
    Word_Object wordObject = new Word_Object("Test");
    wordObject.setStart_index(5);
    assert wordObject.getStart_index() == 5;
    System.out.println("testSetStartIndex success");
}

private static void testSetEndIndex() {
    Word_Object wordObject = new Word_Object("Test");
    wordObject.setEnd_index(10);
    assert wordObject.getEnd_index() == 10;
    System.out.println("testSetEndIndex success");
}

private static void testGetStartIndex() {
    Word_Object wordObject = new Word_Object("Test");
    assert wordObject.getStart_index() == 0; // Default value is 0
    System.out.println("testGetStartIndex success");
}

private static void testGetEndIndex() {
    Word_Object wordObject = new Word_Object("Test");
    assert wordObject.getEnd_index() == 0; // Default value is 0
    System.out.println("testGetEndIndex success");
}

private static void testGetPrevNode() {
    Word_Object wordObject1 = new Word_Object("Test1");
    Word_Object wordObject2 = new Word_Object("Test2");
    wordObject1.setPrev_node(wordObject2);
    assert wordObject1.getPrev_node() == wordObject2;
    System.out.println("testGetPrevNode success");
}

private static void testSetPrevNode() {
    Word_Object wordObject1 = new Word_Object("Test1");
    Word_Object wordObject2 = new Word_Object("Test2");
    wordObject1.setPrev_node(wordObject2);
    assert wordObject2.getNext_node() == wordObject1;
    System.out.println("testSetPrevNode success");
}

private static void testGetNextNode() {
    Word_Object wordObject1 = new Word_Object("Test1");
    Word_Object wordObject2 = new Word_Object("Test2");
    wordObject1.setNext_node(wordObject2);
    assert wordObject1.getNext_node() == wordObject2;
    System.out.println("testGetNextNode success");
}

private static void testSetNextNode() {
    Word_Object wordObject1 = new Word_Object("Test1");
    Word_Object wordObject2 = new Word_Object("Test2");
    wordObject1.setNext_node(wordObject2);
    assert wordObject2.getPrev_node() == wordObject1;
    System.out.println("testSetNextNode success");
}

private static void testGetWord() {
    Word_Object wordObject = new Word_Object("Test");
    assert wordObject.getWord().equals("Test");
    System.out.println("testGetWord success");
}

private static void testSetEndsWithPunctuation() {
    Word_Object wordObject = new Word_Object("Test");
    wordObject.setEndsWithPunctuation(true);
    assert wordObject.getEndsWithPunctuation();
    System.out.println("testSetEndsWithPunctuation success");
}

private static void testGetEndsWithPunctuation() {
    Word_Object wordObject = new Word_Object("Test");
    assert !wordObject.getEndsWithPunctuation(); // Default value is false
    System.out.println("testGetEndsWithPunctuation success");
}

private static void testSetIsFirstWord() {
    Word_Object wordObject = new Word_Object("Test");
    wordObject.setIs_first_word(true);
    assert wordObject.isIs_first_word();
    System.out.println("testSetIsFirstWord success");
}

private static void testIsIsFirstWord() {
    Word_Object wordObject = new Word_Object("Test");
    assert !wordObject.isIs_first_word(); // Default value is false
    System.out.println("testIsIsFirstWord success");
}

private static void testSetWord() {
    Word_Object wordObject = new Word_Object("Test");
    wordObject.setWord("NewTest");
    assert wordObject.getWord().equals("NewTest");
    assert wordObject.getIs_capital_at()[0] == 1; // First character is uppercase
    System.out.println("testSetWordAndUpdateProperties success");
}

    private static void testReplaceWord() {
        Word_Object wordObject1 = new Word_Object("Test1");
        Word_Object wordObject2 = new Word_Object("Test2");
        wordObject1.setNext_node(wordObject2);

        wordObject1.setStart_index(5);
        wordObject1.setEnd_index(10);

        wordObject1.replace_word("NewTest");

        assert wordObject1.getWord().equals("NewTest");
        assert wordObject1.getStart_index() == 5;
        assert wordObject1.getEnd_index() == 12; // (5 + length of "NewTest" - 1)
        assert wordObject1.getIs_capital_at()[0] == 1; // First character is uppercase

        assert wordObject2.getStart_index() == 13; // (previous end index + 1)
        assert wordObject2.getEnd_index() == 18; // (start index + length of "Test2" - 1)

        assert wordObject1.isModified();
        assert wordObject2.isModified();

        System.out.println("testReplaceWord success");
    }

    private static void testSetCapitalAt() {
        Word_Object wordObject = new Word_Object("Test");
        int[] capitalAt = {1, 0, 1, 0}; // Example array
        wordObject.setCapital_at(capitalAt);
        assert wordObject.getIs_capital_at() == capitalAt;
        System.out.println("testSetCapitalAt success");
    }

    private static void testSetCapitalMarkets() {
        Word_Object prevWordObject = new Word_Object("Prev");
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setPrev_node(prevWordObject);

        // Set conditions for needs_first_capital, needs_lower_but_first, needs_lower
        prevWordObject.setEnd_with_period(true);
        prevWordObject.setWord("prev");
        wordObject.setWord("Test");
        wordObject.set_capital_markets();

        assert wordObject.isNeeds_first_capital();
        assert !wordObject.isNeeds_lower_but_first();
        assert wordObject.isNeeds_lower();

        System.out.println("testSetCapitalMarkets success");
    }

    private static void testEndWithPeriod() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setEnd_with_period(true);
        assert wordObject.isEnd_with_period();
        System.out.println("testEndWithPeriod success");
    }

    private static void testIsRealWord() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setIs_real_word(true);
        assert wordObject.isIs_real_word();
        System.out.println("testIsRealWord success");
    }

    private static void testNeedsCapital() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setNeeds_capital(true);
        assert wordObject.isNeeds_capital();
        System.out.println("testNeedsCapital success");
    }

    private static void testNeedsPeriod() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setNeeds_period(true);
        assert wordObject.isNeeds_period();
        System.out.println("testNeedsPeriod success");
    }

    private static void testSetSpacesAfter() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setSpaces_after(2);
        assert wordObject.getSpaces_after() == 2;
        System.out.println("testSetSpacesAfter success");
    }

    private static void testSetSpacesBefore() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setSpaces_before(3);
        assert wordObject.getSpaces_before() == 3;
        System.out.println("testSetSpacesBefore success");
    }

    private static void testIsDoubleWordAfter() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setIs_double_word_after(true);
        assert wordObject.isIs_double_word_after();
        System.out.println("testIsDoubleWordAfter success");
    }

    private static void testIsDoubleWordBefore() {
        Word_Object wordObject = new Word_Object("Test");
        wordObject.setIs_double_word_before(true);
        assert wordObject.isIs_double_word_before();
        System.out.println("testIsDoubleWordBefore success");
    }

    private static void testSetSuggestions() {
        Word_Object wordObject = new Word_Object("Test");
        String[] suggestions = {"Suggestion1", "Suggestion2", "Suggestion3"};
        wordObject.setSuggestions(suggestions);
        assert wordObject.getSuggestion_1().equals("Suggestion1");
        assert wordObject.getSuggestion_2().equals("Suggestion2");
        assert wordObject.getSuggestion_3().equals("Suggestion3");
        System.out.println("testSetSuggestions success");
    }

    private static void testGetSuggestions() {
        Word_Object wordObject = new Word_Object("Test");
        String[] suggestions = {"Suggestion1", "Suggestion2", "Suggestion3"};
        wordObject.setSuggestions(suggestions);
        String[] retrievedSuggestions = wordObject.getSuggestions();
        assert retrievedSuggestions[0].equals("Suggestion1");
        assert retrievedSuggestions[1].equals("Suggestion2");
        assert retrievedSuggestions[2].equals("Suggestion3");
        System.out.println("testGetSuggestions success");
    }

    private static void testGetIsCapitalAt() {
        Word_Object wordObject = new Word_Object("Test");
        int[] capitalAt = {1, 0, 1, 0}; // Example array
        wordObject.setCapital_at(capitalAt);
        assert wordObject.getIs_capital_at() == capitalAt;
        System.out.println("testGetIsCapitalAt success");
    }

    private static void testHasNext() {
        Word_Object wordObject = new Word_Object("Test");
        assert !wordObject.hasNext();
        wordObject.setNext_node(new Word_Object("NextTest"));
        assert wordObject.hasNext();
        System.out.println("testHasNext success");
    }

}