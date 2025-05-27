package groupx.hw4.chordfinder.controller;

//spring frameworks
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import groupx.hw4.chordfinder.model.ChordFinderForm;
import groupx.hw4.chordfinder.service.ChordFinderService;

@Controller
public class ChordFinderController {

    private final ChordFinderService chordFinderService;

    //constructor 
    public ChordFinderController(ChordFinderService chordFinderService) {
    	this.chordFinderService = chordFinderService;
    }
    
    //main page
    
    @GetMapping("/")
    public String index(Model model) {
    	//adding a form object to the model
    	model.addAttribute("chordFinderForm", new ChordFinderForm());
    	//added rulesFile info
    	model.addAttribute("rulesFile", chordFinderService.getRulesFile());
    	return "index"; //return the index
    }
    
    //so basically after inputting the notes and method into the form, it needs to process it w/ our service,
    
    // results page or 'FindChords' page
    @PostMapping("/findChords")
    public String findChords(@ModelAttribute ChordFinderForm chordFinderForm, Model model) {
    	//spring is mapping the data to the chordFinderForm parameter using @ModelAttribute
    	
    	try {
    		
    		List<String> notes = chordFinderService.processNotes(chordFinderForm.getNotes());
    		
    		Set<String> chords = chordFinderService.findChords(notes);
    		
    		String resultMessage;

    		if (chords.isEmpty()) {
    			resultMessage = "No matching chords for your notes: [ " + chordFinderForm.getNotes() + " ]";
    		} else {
    			resultMessage = "Matching chords for your notes: [ " + chordFinderForm.getNotes() + " ]: " + chords;
    		}
    		
    		//attributes to add
    		model.addAttribute("chords", chords);
    		model.addAttribute("notes", notes);
    		model.addAttribute("resultMessage", resultMessage);
    		model.addAttribute("rulesFile", chordFinderService.getRulesFile());
    		
    		
    	} catch (IllegalArgumentException e) {
    		model.addAttribute("errorMessage", e.getMessage());
    	}
    	
    	//keeping the form data
    	model.addAttribute("chordFinderForm", chordFinderForm);
    	
    	return "result";
    }
}
