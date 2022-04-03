package fsm;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.stream.Stream;

import entities.LEDStrip;
import entities.Pixel;

public class SundialState extends State {

	public SundialState(LEDStrip ledStrip, Consumer<LEDStrip> change) {
		super(ledStrip, change);
	}

	@Override
	public Pixel[] display() {
		getLedStrip().setAmbient(1.0f);
		getChange().accept(getLedStrip());
		return null;
	}

	@Override
	public Pixel[] reactOnMotion() {
		LocalDateTime currentTime = getCurrentTime();
		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();
		int second = currentTime.getSecond();
		
		getHourHand().setHour(hour, minute);
		getMinuteHand().setMinute(minute, second);
		getSecondHand().setSecond(second);
		getHourHand().switchBy180Degrees();
		getMinuteHand().switchBy180Degrees();
		getSecondHand().switchBy180Degrees();
		getLedStrip().clear();
		getLedStrip().setHand(getHourHand());
		getLedStrip().setHand(getMinuteHand());
		getLedStrip().setHand(getSecondHand());

		getChange().accept(getLedStrip());
		return null;
	}

	@Override
	public void start(int lent) {
		// TODO Auto-generated method stub

	}

}
