package tests;
import Backend.Document;
import Backend.Word_Object;
import Backend.Doc_Analysis;
import Backend.Doc_Error;

public class Doc_Analysis_test {

    public void testDocAnalysis() {
    

    //create a linked list of words

    String test_corpus = "The Quick brown fox Jumps over the lazy dog.\n" +
    "it's a beautifull sunny day outside.\n" +
    "Tommorrow will be an an intersting day for our picnic.\n" +
    "She said her favoritte book is \"The Great Gatsby\".\n" +
    "Could you pleese pass the ketchup?\n" +
    "He decided to seperate the papers into two piles.\n" +
    "Their going to visit the museum next week.\n" +
    "The cat chased it's tail around the living room.\n" +
    "We recieved an invitation for the gaLlery opening.\n" +
    "He loves to play the guitar and the pianoo.";



    //create a doc object
    StringBuilder stringBuilder = new StringBuilder();
        for (String word : test_corpus) {
        stringBuilder.append(word);
        stringBuilder.append(" ");
        }
    String result = stringBuilder.toString().trim();
    Document doc = new Document(result);

    //now create doc analysis object, which will in turn create a linked list of words
    Doc_Analysis doc_analysis = new Doc_Analysis(doc.wordBuffer);

    //test get line count
    int lc = doc_analysis.get_line_count();

    //correct metric of lines given by the test corpus is 10
    if(lc == 10){
        System.out.println("Test 1: Passed");
    }else{
        System.out.println("Test 1: Failed");
    }

    //test get word count
    int wc = doc_analysis.get_word_count();
    //correct metric of words given by the test corpus is 80
    if(wc == 80){
        System.out.println("Test 2: Passed");
    }else{
        System.out.println("Test 2: Failed");
    }

    //test get char count
    int cc = doc_analysis.get_char_count();
    //correct metric of chars given by the test corpus is 399
    if(cc == 399){
        System.out.println("Test 3: Passed");
    }else{
        System.out.println("Test 3: Failed");
    }
}
}
