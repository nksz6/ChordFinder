package groupx.hw4.chordfinder.service;

import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.InitializingBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import groupx.hw4.chordfinder.ChordFinder;
import groupx.hw4.chordfinder.model.Note;
import groupx.hw4.chordfinder.model.ChordRules;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//ties together web interface, JSON configs, and ChordFinder logic.

@Service
public class ChordFinderService implements InitializingBean {
	//had to implement InitializingBean due to issue
	
	private final ChordFinder chordFinder;
	
	//new method 1 is now only 'ChordRule'.
	private Map<String, List<Integer>> chordRules;
	
	//config property
	@Value("${chordfinder.rules.file:chord_rules_method1.json}") //line 7
	private String rulesFile;
	
	//default service
	public ChordFinderService() {
		this.chordFinder = new ChordFinder();
	}
	
	//called by spring after all properties are set, seems unnecessary but solved issue
	@Override
	public void afterPropertiesSet() throws Exception {
		loadChordRules();
	}
	
	//ONLY load the new chord rule
	private void loadChordRules() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			//LOAD FROM NEW JSON
			InputStream is = new ClassPathResource(rulesFile).getInputStream();
			ChordRules rules = objectMapper.readValue(is, ChordRules.class);
			
			//CONVERT
			this.chordRules = rules.toIntervalMap();
			
			} catch (IOException e) {
				throw new RuntimeException("Failed to load chord rules from: " + rulesFile, e);
			}
	}
	
	  /**
     * Find chords based on the input notes
     * @param notes List of notes
     * @return Set of chord names
     */
	
	public Set<String> findChords(List<String> notes) {
		
		//make sure input is valid, input not empty, & between 3 and 7.
		
		if (notes == null || notes.isEmpty()) {
            throw new IllegalArgumentException("No notes provided");
        }
        
        if (notes.size() < 3 || notes.size() > 7) {
            throw new IllegalArgumentException("Number of notes must be between 3 and 7");
        }
		
        for (String note : notes) {
            if (!isValidNote(note)) { //isValidNote method is at the bottom of file
                throw new IllegalArgumentException("Invalid note: " + note);
            }
        }
        
        return chordFinder.findChords(notes, chordRules, "Method1");
	}
	
	
	 /**
     * Process notes from a string input
     * @param input String containing notes separated by spaces or commas
     * @return List of processed notes
     */
	  public List<String> processNotes(String input) {
		  
		  //check
		  if (input == null || input.trim().isEmpty()) {
	            throw new IllegalArgumentException("Note input cannot be empty");
	        }
		  
		  //Remove extra spaces and commas
		  String[] noteArray = input.trim().replaceAll("\\s*,\\s*", " ").split("\\s+");
		  List<String> notes = new ArrayList<>();
		  
		  //make sure the trimmed notes are valid
		  for (String note : noteArray) {
			  String trimmedNote = note.trim();
			  if (!isValidNote(trimmedNote)) {
				  throw new IllegalArgumentException("Invalid note: " + trimmedNote);
			  }
			  notes.add(trimmedNote); //then add
		  }
		  
		  //make sure size is still fine after trimming
		  if (notes.size() < 3 || notes.size() > 7) {
			  throw new IllegalArgumentException("Number of notes is not between 3 and 7.");
		  }
		  
		  return notes;
	   
	  }
	
	/**
     * Check if a note is valid
     * @param note The note to check
     * @return true if valid, false otherwise
     */
    public boolean isValidNote(String note) {
        return Note.validateNote(note);
    }
    
    //GETTER & SETTER FOR CURRENT RULES FILE
    public String getRulesFile() {
    	return rulesFile;
    }
    public void setRulesFile(String rulesFile) {
    	this.rulesFile = rulesFile;
    	loadChordRules();
    }
}
