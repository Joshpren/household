package entities;

public class SecondHand extends Hand {

	public SecondHand(int numberOfLED) {
		super(numberOfLED, 90, 255, 255, 255);
	}

	public void setSecond(int seconds) {
		double multiplicator = getNumberOfLED() / 60;
		setIndex((int) (seconds * multiplicator));
	}

}
