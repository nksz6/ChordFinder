package groupx.hw4.chordfinder;

import java.util.*;

import groupx.hw4.chordfinder.model.Note;

public class ChordFinder { 
	
	/**
     * Finds all possible chords from the given notes.
     * @param notes List of notes to analyze
     * @param chordRules Map of chord names to their interval patterns
     * @param method Method to use for chord identification (1 or 2)
     * @return Set of chord names that match the given notes
     */
	
	public Set<String> findChords(List<String> notes, Map<String, List<Integer>> chordRules, String method) {
		if (notes == null || notes.isEmpty() || notes.size() < 3) { //MUST give atleast 3 notes
			throw new IllegalArgumentException("Input must be atleast 3 notes");
		}
		
		
		//check and make sure the notes are valid
		for (String note : notes) {
			if (!Note.validateNote(note)) {
				throw new IllegalArgumentException(note + "is not a note.");
			}
		}
		
		Set<String> results = new HashSet<>();
		
		for (String rootNote : notes) {
			Set<String> chordsForRoot = findChordsWithRoot(rootNote, notes, chordRules, method);
			results.addAll(chordsForRoot);
		}
		
		return results;
	}
	
	/**
     * Finds chords with a specific root note.
     * @param rootNote The root note to use
     * @param allNotes All notes in the chord
     * @param chordRules Map of chord names to their interval patterns
     * @param method Method to use (1 or 2)
     * @return Set of chord names with the given root
     * 
     * 
     */
	
	private Set<String> findChordsWithRoot(String rootNote, List<String> allNotes, Map<String, List<Integer>> chordRules, String method) {
	    Set<String> results = new HashSet<>();

	    if (method.equalsIgnoreCase("Method1")) {
	        List<String> orderedNotes = new ArrayList<>();
	        orderedNotes.add(rootNote);

	        for (String note : allNotes) {
	            if (!note.equals(rootNote) && !orderedNotes.contains(note)) {
	                orderedNotes.add(note);
	            }
	        }

	        List<Integer> adjacentIntervals = new ArrayList<>();
	        for (int i = 0; i < orderedNotes.size() - 1; i++) {
	            adjacentIntervals.add(Note.getDistance(orderedNotes.get(i), orderedNotes.get(i + 1)));
	        }

	        for (Map.Entry<String, List<Integer>> entry : chordRules.entrySet()) {
	            String chordName = entry.getKey();
	            List<Integer> chordPattern = entry.getValue();
	            if (adjacentIntervals.equals(chordPattern)) {
	                results.add(rootNote + " " + chordName);
	            }
	        }
	    }

	    return results;
	}

    /**
     * Processes all possible permutations of notes as potential chords.
     * @param notes List of notes to analyze
     * @return Map of interval sets to root notes
     */
	
	public Map<Set<Integer>, String> chordPermutations(List<String> notes) { 	//changed to list instead of arraylist
		Map<Set<Integer>, String> potentialChords = new HashMap<>(); //same as potChordMap
		
		for (String rootNote : notes) {
			Set<Integer> intervals = new HashSet<>();
			
			for(String otherNote : notes) {
				if (!otherNote.equals(rootNote)) {
					intervals.add(Note.getDistance(rootNote, otherNote));
				}
			}
			potentialChords.put(intervals, rootNote);
		}
		return potentialChords;
	}
}