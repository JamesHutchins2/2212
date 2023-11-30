package tests;

public class main {
    //this class will be the main class. It will run all the tests

    public static void main(String[] args){
        //run the tests
        //run the dictionary test
        Dictionary_test dict_test = new Dictionary_test();
        dict_test.testDictionary();

        //run the doc analysis test
        Doc_Analysis_test doc_analysis_test = new Doc_Analysis_test();
        doc_analysis_test.testDocAnalysis();


        //run the doc error test
        Doc_Error_test doc_error_test = new Doc_Error_test();
        doc_error_test.testDocError();

        System.out.println("All tests passed!");

    }
    
}
