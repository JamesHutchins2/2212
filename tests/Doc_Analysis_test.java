package tests;
import Backend.Document;
public class Doc_Analysis_test {

    public void testDocAnalysis() {
    

    //create a linked list of words

    String test_corpus = "The Quick brown fox Jumps over the lazy dog.\n"
                   + "it's a beautifull sunny day outside.\n"
                   + "Tommorrow will be an intersting day for our picnic.\n"
                   + "She said her favoritte book is \"The Great Gatsby\".\n"
                   + "Could you pleese pass the ketchup?\n"
                   + "He decided to seperate the papers into two piles.\n"
                   + "Their going to visit the museum next week.\n"
                   + "The cat chased it's tail around the living room.\n"
                   + "We recieved an invitation for the gallery opening.\n"
                   + "He loves to play the guitar and the pianoo.";



    // convert the test_corpus into a linked list of words

    //create a doc object

    Document doc = new Document(test_corpus);



    }
}
