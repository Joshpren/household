package entities;

public class SecondHand extends Hand {

	private int multiplicator;
	private int lastSecond = -1;
	private int counter = 0;

	public SecondHand(int numberOfLED) {
		super(numberOfLED, 90, 255, 255, 255);
		this.multiplicator = getNumberOfLED() / 60;
	}

	public void setSecond(int seconds) {
		if (lastSecond == seconds) {
			counter++;
		} else {
			this.lastSecond = seconds;
			counter = 0;
		}

		setIndex((int) (seconds * multiplicator) + counter);
	}

}
