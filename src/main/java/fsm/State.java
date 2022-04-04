package fsm;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import entities.HourHand;
import entities.LEDStrip;
import entities.MinuteHand;
import entities.Pixel;
import entities.SecondHand;
import io.vertx.core.Vertx;
import lombok.Getter;

@Getter
public abstract class State {

	private final Vertx vertx;
	private long vertxTimerDuration;
	private final LEDStrip ledStrip;
	private final HourHand hourHand;
	private final MinuteHand minuteHand;
	private final SecondHand secondHand;
	private Consumer<LEDStrip> change;
	private final Consumer<Long> currentTimer;

	public State(Vertx vertx, Consumer<Long> currentTimer, LEDStrip ledStrip, Consumer<LEDStrip> change) {
		this.vertx = vertx;
		this.currentTimer = currentTimer;
		this.ledStrip = ledStrip;
		this.hourHand = new HourHand(ledStrip.getLength());
		this.minuteHand = new MinuteHand(ledStrip.getLength());
		this.secondHand = new SecondHand(ledStrip.getLength());
		this.change = change;
		this.vertxTimerDuration = (long) ((float) 1000 * ((float) 60 / (float) ledStrip.getLength()));
		System.out.println(vertxTimerDuration);
	}

	public void setCurrentTimer(long currentTimer) {
		this.currentTimer.accept(currentTimer);
	}

	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}

	public abstract void display();

	public abstract void reactOnMotion();

	public abstract void start();

}
