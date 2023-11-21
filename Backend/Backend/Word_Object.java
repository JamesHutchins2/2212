package Backend;
public class Word_Object{
    private Word_Object prev_node;
    private Word_Object next_node;
    private String word;
    private boolean start_with_capital;
    private boolean end_with_period;
    private boolean is_real_word;
    private boolean needs_capital;
    private boolean needs_period;
    private boolean is_double_word_after;
    private boolean is_double_word_before;
    private String suggestion_1;
    private String suggestion_2;
    private String suggestion_3;

    public Word_Object(){
        //do nothing
    }
    
    public Word_Object(String word, boolean start_with_capital, boolean end_with_period, boolean is_real_word, boolean needs_capital, boolean needs_period, boolean is_double_word_after, boolean is_double_word_before, String suggestion_1, String suggestion_2, String suggestion_3) {
        this.word = word;
        this.start_with_capital = start_with_capital;
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

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isStart_with_capital() {
        return start_with_capital;
    }

    public void setStart_with_capital(boolean start_with_capital) {
        this.start_with_capital = start_with_capital;
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
        this.suggestion_1 = suggestions[0];
        this.suggestion_2 = suggestions[1];
        this.suggestion_3 = suggestions[2];
    }
    public String[] getSuggestions(){
        String[] items = new String[3];
        items[0] = this.suggestion_1;
        items[1] = this.suggestion_2;
        items[2] = this.suggestion_3;

        return items;

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
