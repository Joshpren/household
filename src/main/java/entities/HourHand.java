package entities;

public class HourHand extends Hand {

	public HourHand(int numberOfLED) {
		super(numberOfLED);
	}
	
	public void setHour(int militaryHour, int minutes) {
		int numberOfLEDsBetweenTwoHours = getNumberOfLED() / TWELVE_HOUR_SYSTEM;
		int ordinalClockRepresentation = militaryHour - TWELVE_HOUR_SYSTEM;
		int delay =(int)((float) numberOfLEDsBetweenTwoHours * ((float) minutes /(float) 60));
		int precisePosition = (ordinalClockRepresentation * numberOfLEDsBetweenTwoHours) +delay;
		setIndex(precisePosition);
	}
}
