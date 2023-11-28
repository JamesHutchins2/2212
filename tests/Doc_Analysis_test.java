package tests;
import Backend.Document;
import Backend.Doc_Analysis;
import Backend.Doc_Error;
public class Doc_Analysis_test {

    public void testDocAnalysis() {
    

    //create a linked list of words

    String [] test_corpus = {"The", "Quick", "brown", "fox", "Jumps", "over", "the", "lazy", "dog.", "\n",
        "it's", "a", "beautifull", "sunny", "day", "outside.", "\n",
        "Tommorrow", "will", "be", "an", "intersting", "day", "for", "our", "picnic.", "\n",
        "She", "said", "her", "favoritte", "book", "is", "\"The", "Great", "Gatsby\".", "\n",
        "Could", "you", "pleese", "pass", "the", "ketchup?", "\n",
        "He", "decided", "to", "seperate", "the", "papers", "into", "two", "piles.", "\n",
        "Their", "going", "to", "visit", "the", "museum", "next", "week.", "\n",
        "The", "cat", "chased", "it's", "tail", "around", "the", "living", "room.", "\n",
        "We", "recieved", "an", "invitation", "for", "the", "gallery", "opening.", "\n",
        "He", "loves", "to", "play", "the", "guitar", "and", "the", "pianoo."};



    //create a doc object

    Document doc = new Document(test_corpus);

    //now create doc analysis object, which will in turn create a linked list of words
    Doc_Analysis doc_analysis = new Doc_Analysis(doc);

    //test get line count
    int lc = doc_analysis.getLineCount();

    //correct metric of lines given by the test corpus is 10
    if(lc == 10){
        System.out.println("Test 1: Passed");
    }else{
        System.out.println("Test 1: Failed");
    }

    //test get word count
    int wc = doc_analysis.getWordCount();
    //correct metric of words given by the test corpus is 80
    if(wc == 80){
        System.out.println("Test 2: Passed");
    }else{
        System.out.println("Test 2: Failed");
    }

    //test get char count
    int cc = doc_analysis.getCharCount();
    //correct metric of chars given by the test corpus is 399
    if(cc == 399){
        System.out.println("Test 3: Passed");
    }else{
        System.out.println("Test 3: Failed");
    }
}
}
