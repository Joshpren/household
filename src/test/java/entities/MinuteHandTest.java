package entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MinuteHandTest {

	@Test
	void setMinutes() {
		int numberOfLED = 60;
		int minutes = 20;
		int seconds = 0;
		int expectedIndex = 20;
		MinuteHand cut = new MinuteHand(numberOfLED);
		cut.setMinute(minutes, seconds);
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 120;
		minutes = 21;
		seconds = 30;
		expectedIndex = 43;
		cut = new MinuteHand(numberOfLED);
		cut.setMinute(minutes, seconds);
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 120;
		minutes = 21;
		seconds = 50;
		expectedIndex = 43;
		cut = new MinuteHand(numberOfLED);
		cut.setMinute(minutes, seconds);
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 120;
		minutes = 22;
		seconds = 0;
		expectedIndex = 44;
		cut = new MinuteHand(numberOfLED);
		cut.setMinute(minutes, seconds);
		assertEquals(expectedIndex, cut.getIndex());
	}
	

	@Test
	void switchBy180Degrees() {
		int numberOfLED = 120;
		int minutes = 21;
		int seconds = 0;
		int expectedIndex = 42;
		MinuteHand cut = new MinuteHand(numberOfLED);
		cut.setMinute(minutes, seconds);
		assertEquals(expectedIndex, cut.getIndex());
		
		cut.switchBy180Degrees();
		expectedIndex = 102;
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 60;
		minutes = 13;
		seconds = 55;
		expectedIndex = 13;
		cut = new MinuteHand(numberOfLED);
		cut.setMinute(minutes, seconds);
		assertEquals(expectedIndex, cut.getIndex());
		
		cut.switchBy180Degrees();
		expectedIndex = 43;
		assertEquals(expectedIndex, cut.getIndex());
		
	}
}
