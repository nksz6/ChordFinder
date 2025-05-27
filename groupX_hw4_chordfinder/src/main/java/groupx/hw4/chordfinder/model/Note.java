package groupx.hw4.chordfinder.model;

import java.util.HashMap;
import java.util.Map;

public class Note {
	
	private static final Map<String, Integer> pitch = new HashMap<>(); // static to be used in the main method
	//final since this ain't gonna change.
	
	//pitch mapping
	static {
		pitch.put("A", 0);
		pitch.put("A#", 1);
		pitch.put("Bb", 1);
		pitch.put("B", 2);
		pitch.put("B#", 3);
		pitch.put("Cb", 2);
		pitch.put("C", 3);
		pitch.put("C#", 4);
		pitch.put("Db", 4);
		pitch.put("D", 5);
		pitch.put("D#", 6);
		pitch.put("Eb", 6);
		pitch.put("E", 7);
		pitch.put("E#", 8);
		pitch.put("Fb", 7);
		pitch.put("F", 8);
		pitch.put("F#", 9);
		pitch.put("Gb", 9);
		pitch.put("G", 10);
		pitch.put("G#", 11);
		pitch.put("Ab", 11);
	}

	//Calculate distance between two notes, return distance
	public static int getDistance(String note1, String note2) {
		if (!pitch.containsKey(note1) || !pitch.containsKey(note2)) { //Make sure note is valid!
			throw new IllegalArgumentException("Invalid Note: " + note1 + " and/or " + note2); //throw exception if not + say what wasn't valid.
		}
		int distance = 0; //initialize distance
		if (pitch.get(note2) < pitch.get(note1)) { //if note2 less than note1, add 12 for the wrap-around effect...
			distance = pitch.get(note2) + 12 - pitch.get(note1);
		} else {
			distance = pitch.get(note2) - pitch.get(note1); // if note1 is already less just subtract it.
		}
		return distance;
	}
	
	//makes sure note is valid
	public static boolean validateNote(String note) {
		return (note != null && pitch.containsKey(note)); //will return true or false
	}
}