package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public abstract class Hand {

	public static final int TWELVE_HOUR_SYSTEM = 12;
	@Setter
	private int index = 0;
	@Setter
	private float brightness;
	@Getter
	private int red;
	@Getter
	private int blue;
	@Getter
	private int green;

	private final int numberOfLED;

	public Hand(int numberOfLED, float brightness, int red, int blue, int green) {
		this.brightness = brightness;
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.numberOfLED = numberOfLED;
	}

	public void switchBy180Degrees() {
		int newIndex = ((index + numberOfLED / 2) % numberOfLED);
		setIndex(newIndex);
	}
}
