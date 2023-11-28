package Backend;



public class Word_Object{
    private Word_Object prev_node;
    private Word_Object next_node;
    private String word;
    private boolean needs_first_capital;
    private boolean needs_lower_but_first;
    private boolean needs_lower;
    private boolean end_with_period;
    private boolean is_real_word;
    private boolean needs_capital;
    private boolean needs_period;
    private boolean is_double_word_after;
    private boolean is_double_word_before;
    private String suggestion_1;
    private String suggestion_2;
    private String suggestion_3;
    private int start_index;
    private int end_index;
    private int spaces_after;
    private int spaces_before;
    private int[] is_capital_at;
    private boolean ends_with_punctuation;
    private boolean is_first_word;

    public Word_Object(String word) {
        // Initialization logic
        //set is_real_word to false
        this.is_real_word = false;
        this.word = word;
        this.end_with_period = false;
        this.needs_capital = false;
        this.needs_period = false;
        this.is_double_word_after = false;
        this.is_double_word_before = false;
        this.suggestion_1 = "";
        this.suggestion_2 = "";
        this.suggestion_3 = "";
        this.start_index = 0;
        this.end_index = 0;
        this.spaces_after = 0;
        this.spaces_before = 0;
        this.is_capital_at = null;
        this.ends_with_punctuation = false;
        this.is_first_word = false;
        this.needs_first_capital = false;
        this.needs_lower_but_first = false;
        this.needs_lower = false;


    }

    private boolean modified = true;

    public boolean isNeeds_first_capital() {
        return needs_first_capital;
    }

    public void setNeeds_first_capital(boolean needs_first_capital) {
        this.needs_first_capital = needs_first_capital;
    }

    public boolean isNeeds_lower_but_first() {
        return needs_lower_but_first;
    }

    public void setNeeds_lower_but_first(boolean needs_lower_but_first) {
        this.needs_lower_but_first = needs_lower_but_first;
    }

    public boolean isNeeds_lower() {
        return needs_lower;
    }

    public void setNeeds_lower(boolean needs_lower) {
        this.needs_lower = needs_lower;
    }


    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    
    public void Word_Object_full(String word, boolean start_with_capital, boolean end_with_period, boolean is_real_word, boolean needs_capital, boolean needs_period, boolean is_double_word_after, boolean is_double_word_before, String suggestion_1, String suggestion_2, String suggestion_3) {
        this.word = word;
        this.end_with_period = end_with_period;
        this.is_real_word = is_real_word;
        this.needs_capital = needs_capital;
        this.needs_period = needs_period;
        this.is_double_word_after = is_double_word_after;
        this.is_double_word_before = is_double_word_before;
        this.suggestion_1 = suggestion_1;
        this.suggestion_2 = suggestion_2;
        this.suggestion_3 = suggestion_3;
    }

    public void setStart_index(int start_index) {
        this.start_index = start_index;
    }

    public void setEnd_index(int end_index) {
        this.end_index = end_index;
    }

    public int getStart_index() {
        return start_index;
    }

    public int getEnd_index() {
        return end_index;
    }
    

    public Word_Object getPrev_node() {
        return prev_node;
    }

    public void setPrev_node(Word_Object prev_node) {
        this.prev_node = prev_node;
    }

    public Word_Object getNext_node() {
        return next_node;
    }

    public void setNext_node(Word_Object next_node) {
        this.next_node = next_node;
    }

    public String getWord() {
        return word;
    }
    public void setEndsWithPunctuation(boolean endsWithPunctuation) {
        this.ends_with_punctuation = endsWithPunctuation;
    }
    public boolean getEndsWithPunctuation() {
        return ends_with_punctuation;
    }

    public void setIs_first_word(boolean is_first_word) {
        this.is_first_word = is_first_word;
    }

    public boolean isIs_first_word() {
        return is_first_word;
    }

    public void setWord(String word) {
        this.word = word;
        this.is_capital_at = new int[word.length()];
        //loop through each char assign 1 if capital, 0 if not
        for(int i = 0; i < word.length(); i++){
            if(Character.isUpperCase(word.charAt(i))){
                this.is_capital_at[i] = 1;
            }
            else{
                this.is_capital_at[i] = 0;
            }
        }
    }

    

    
    public boolean isEnd_with_period() {
        return end_with_period;
    }

    public void setEnd_with_period(boolean end_with_period) {
        this.end_with_period = end_with_period;
    }

    public boolean isIs_real_word() {
        return is_real_word;
    }

    public void setIs_real_word(boolean is_real_word) {
        this.is_real_word = is_real_word;
    }

    public boolean isNeeds_capital() {
        return needs_capital;
    }

    public void setNeeds_capital(boolean needs_capital) {
        this.needs_capital = needs_capital;
    }

    public boolean isNeeds_period() {
        return needs_period;
    }

    public void setNeeds_period(boolean needs_period) {
        this.needs_period = needs_period;
    }

    public void setSpaces_after(int spaces_after) {
        this.spaces_after = spaces_after;
    }
    public void setSpaces_before(int spaces_before) {
        this.spaces_before = spaces_before;
    }

    public int getSpaces_after() {
        return spaces_after;
    }

    public int getSpaces_before() {
        return spaces_before;
    }

    public boolean isIs_double_word_after() {
        return is_double_word_after;
    }

    public void setIs_double_word_after(boolean is_double_word_after) {
        this.is_double_word_after = is_double_word_after;
    }

    public boolean isIs_double_word_before() {
        return is_double_word_before;
    }

    public void setIs_double_word_before(boolean is_double_word_before) {
        this.is_double_word_before = is_double_word_before;
    }


    public void setSuggestions(String[] suggestions) {
        this.suggestion_1 = suggestions.length > 0 ? suggestions[0] : "";
        this.suggestion_2 = suggestions.length > 1 ? suggestions[1] : "";
        this.suggestion_3 = suggestions.length > 2 ? suggestions[2] : "";
    }
    public String[] getSuggestions(){
        String[] items = new String[3];
        items[0] = this.suggestion_1;
        items[1] = this.suggestion_2;
        items[2] = this.suggestion_3;

        return items;

    }

    public int[] getIs_capital_at() {
        return is_capital_at;
    }

   

    public boolean hasNext(){
        if(this.next_node == null){
            return false;
        }
        else{
            return true;
        }
    }

    
}
