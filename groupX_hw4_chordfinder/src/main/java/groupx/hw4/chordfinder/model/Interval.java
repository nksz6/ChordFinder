package groupx.hw4.chordfinder.model;

//enum file to match intervals w/ semitone values
//e.g., MAJOR THIRD -> 4


public enum Interval {
	
	//standard intervals w/ terms
	MINOR_SECOND(1),
	MAJOR_SECOND(2),
	MINOR_THIRD(3),
	MAJOR_THIRD(4),
	PERFECT_FOURTH(5),
	DIMINISHED_FIFTH(6),
	PERFECT_FIFTH(7),
	AUGMENTED_FIFTH(8),
	MINOR_SIXTH(8),
	MAJOR_SIXTH(9),
	MINOR_SEVENTH(10),
	MAJOR_SEVENTH(11),
	OCTAVE(12);
	
	private final int semitones;
	
	//constructor
	Interval(int semitones) {
		this.semitones = semitones;
	}
	
	//getter for semitone value
	public int getSemitones() {
		return semitones;
	}
	
	//conversion from string name of interval to specified value
	/**
	 * @param intervalName (e.g., MAJOR_THIRD)
	 * @return interval enum value (semi-tone)
	 * @throws exception if name is invalid
	 */
	
	public static Interval fromString(String intervalName) {
		try {
			return Interval.valueOf(intervalName);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid interval name: " + intervalName);
		}
	}
	

    
}
