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

	public static void main(String[] args) {
		Pixel[] lightstrip = new Pixel[60];

		for (int i = 0; i < lightstrip.length; i++) {
			lightstrip[i] = new Pixel();
		}

		LocalDateTime now = LocalDateTime.of(2022, Month.MARCH, 29, 18, 1, 10);
		System.out.println(now);
		int seconds = now.getSecond();
		int minute = now.getMinute() - 1;
		int hour = now.getHour();
		int position = determinePositionOfHour(hour, minute);

		lightstrip[position].setBrightnessInPercent(1.0f);
		lightstrip[position].setRed(255);
		lightstrip[position].setGreen(255);
		lightstrip[position].setBlue(255);

		lightstrip[minute].setBrightnessInPercent(0.8f);
		lightstrip[minute].setRed(255);
		lightstrip[minute].setGreen(255);
		lightstrip[minute].setBlue(255);

		lightstrip[seconds].setBrightnessInPercent(0.9f);
		lightstrip[seconds].setRed(200);
		lightstrip[seconds].setGreen(200);
		lightstrip[seconds].setBlue(200);

		for (int i = 0; i < lightstrip.length; i++) {
			System.out.println(i + 1 + ". " + lightstrip[i]);
		}

		System.out.println("45 auf 15: " + adaptedLightIndex(45));
		System.out.println("10 auf 40: " + adaptedLightIndex(10));
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

}
