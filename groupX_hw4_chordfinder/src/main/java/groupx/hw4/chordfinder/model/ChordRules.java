package groupx.hw4.chordfinder.model;

import java.util.*;

//top-level JSON model, mapping to the 'chords' array from the new JSON
//contains chord definitions

public class ChordRules {
	
	//List o'chords
	private List<ChordDefinition> chords;
	
	//JSON constructor
	public ChordRules() {
	}
	
	//getter & setter for chord definition List
	public List<ChordDefinition> getChords() {
		return chords;
	}
	public void setChords(List<ChordDefinition> chords) {
		this.chords = chords;
	}
	
	//converting chord definitions to a map for existing ChordFinder class
	public Map<String, List<Integer>> toIntervalMap() {
		Map <String, List<Integer>> intervalMap = new HashMap<>();
		
		if(chords != null) {
			for (ChordDefinition chord : chords) {
				intervalMap.put(chord.getDisplay_name(), chord.getIntervalSemitones());
			}
		}
		
		return intervalMap;
	}

	//debug
	//@Override
	//public String toString() {
		//return "ChordRules{chords=" + chords + '}';
	//}
}
