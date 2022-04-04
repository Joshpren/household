package fsm;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import entities.LEDStrip;
import entities.Pixel;
import io.vertx.core.Vertx;

public class OrdinalState extends State {

	public OrdinalState(Vertx vertx, Consumer<Long> currentTimer, LEDStrip ledStrip, Consumer<LEDStrip> change) {
		super(vertx, currentTimer, ledStrip, change);
	}

	@Override
	public void display() {
		getChange().accept(getLedStrip());
		
	}

	public void calculatePositions() {
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

		display();
		setCurrentTimer(getVertx().setTimer(getVertxTimerDuration(), ignored -> calculatePositions()));
	}

	@Override
	public void reactOnMotion() {
	}

	@Override
	public void start() {
		calculatePositions();
	}

}
