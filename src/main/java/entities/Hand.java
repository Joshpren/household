package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Hand {

	public static final int TWELVE_HOUR_SYSTEM = 12;
	@Setter
	private int index = 0;
	@Setter
	private float brightness = 1f;
	private final int numberOfLED;

	public Hand(int numberOfLED) {
		this.numberOfLED = numberOfLED;
	}

	public void switchBy180Degrees() {
		int newIndex = ((index + numberOfLED/2) % numberOfLED);
		setIndex(newIndex);
	}
}
