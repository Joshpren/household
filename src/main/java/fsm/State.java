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
import lombok.Getter;

@Getter
public abstract class State {

	private final LEDStrip ledStrip;
	private final HourHand hourHand;
	private final MinuteHand minuteHand;
	private final SecondHand secondHand;
	private Consumer<LEDStrip> change;

	public State(LEDStrip ledStrip, Consumer<LEDStrip> change) {
		this.ledStrip = ledStrip;
		this.hourHand = new HourHand(ledStrip.getLength());
		this.minuteHand = new MinuteHand(ledStrip.getLength());
		this.secondHand = new SecondHand(ledStrip.getLength());
		this.change = change;
	}

	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}

	public abstract Pixel[] display();

	public abstract Pixel[] reactOnMotion();

	public abstract void start(int lent);

}
