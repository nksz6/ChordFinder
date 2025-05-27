package groupx.hw4.chordfinder.model;

//'backing bean' of Spring, binds w/ html's

public class ChordFinderForm {
    private String notes; //stores the notes which get input by user 
    
    //NEW JSON
    public ChordFinderForm() {
        //only 1 method now
    }
    
    //notes getter
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    //debug
    //@Override
    //public String toString() {
        //return "ChordFinderForm [notes=" + notes + "]";
    //}
}