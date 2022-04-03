package entities;

public class Clock {
	
	/**
	 * Displays the time via LED-Diode. Depends on mode.
	 */
	private final int[] ledStrip = new int[120];
	private final HourHand hourHand = new HourHand(ledStrip.length);
	private final MinuteHand minuteHand = new MinuteHand(ledStrip.length);
	

}
