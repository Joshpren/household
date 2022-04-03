package fsm;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import entities.LEDStrip;
import entities.Pixel;

public class OrdinalState extends State {

	public OrdinalState(LEDStrip ledStrip, Consumer<LEDStrip> change) {
		super(ledStrip, change);
	}

	@Override
	public Pixel[] display() {
		LocalDateTime currentTime = getCurrentTime();
		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();
		int second = currentTime.getSecond();
		
		getHourHand().setHour(hour, minute);
		getMinuteHand().setMinute(minute, second);
		getSecondHand().setSecond(second);
		
		getLedStrip().clear();
		getLedStrip().setHand(getHourHand());
		getLedStrip().setHand(getMinuteHand());
		getLedStrip().setHand(getSecondHand());

		getChange().accept(getLedStrip());
		return  null;
	}
	

	@Override
	public Pixel[] reactOnMotion() {
		return null;
	}

	@Override
	public void start(int lent) {
		display();
	}

}
