package entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pixel {

	private float brightnessInPercent = 0.0f;
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	private int index;
	
	public Pixel(int index) {
		this.index = index;
	}

	private static int adaptedLightIndex(int i) {
		return (i + 30) % 60;
	}

	private static int determinePositionOfHour(int hour, int minute) {
		System.out.println(hour);
		int americanHourCount = hour - 12;
		int delay = 5 * (minute / 60);
		return (americanHourCount * 5) + delay;
	}

	public void setWhite(float brightness) {
		setBlue(255);
		setGreen(255);
		setRed(255);
		setBrightnessInPercent(brightness);
	}

	public void clear() {
		this.red = 0;
		this.blue = 0;
		this.green = 0;
		this.brightnessInPercent = 0;
	}

	public void setConfiguration(Hand hand) {
		this.red = hand.getRed();
		this.blue = hand.getBlue();
		this.green = hand.getGreen();
		this.brightnessInPercent = hand.getBrightness();
	}
}
