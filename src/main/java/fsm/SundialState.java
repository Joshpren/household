package fsm;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.stream.Stream;

import entities.LEDStrip;
import entities.Pixel;
import io.vertx.core.Vertx;

public class SundialState extends State {

	public SundialState(Vertx vertx, Consumer<Long> currentTimer, LEDStrip ledStrip, Consumer<LEDStrip> change) {
		super(vertx, currentTimer, ledStrip, change);
	}

	@Override
	public void display() {
		getChange().accept(getLedStrip());
	}

	@Override
	public void reactOnMotion() {
		calculatePositions();
	}

	public void calculatePositions() {
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

		display();
		setCurrentTimer(getVertx().setTimer(getVertxTimerDuration(), ignored -> calculatePositions()));
	}

	@Override
	public void start() {
		getLedStrip().setAmbient(1.0f);
		getChange().accept(getLedStrip());
	}

}
