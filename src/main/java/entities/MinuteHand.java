package entities;

public class MinuteHand extends Hand {

	public MinuteHand(int numberOfLED) {
		super(numberOfLED);
	}

	public void setMinute(int minute, int seconds) {
		double multiplicator = getNumberOfLED() / 60;
		minute *= multiplicator;
		int delay =(int)((float) multiplicator * ((float) seconds /(float) 60));
		int precisePosition = minute + delay;
		setIndex(precisePosition);
	}
	
}  