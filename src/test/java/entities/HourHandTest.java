package entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HourHandTest {

	@Test
	void setHours() {
		int numberOfLED = 60;
		int hour = 21;
		int minute = 0;
		int expectedIndex = 45;
		HourHand cut = new HourHand(numberOfLED);
		cut.setHour(hour, minute);
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 120;
		hour = 21;
		minute = 30;
		expectedIndex = 95;
		cut = new HourHand(numberOfLED);
		cut.setHour(hour, minute);
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 120;
		hour = 13;
		minute = 55;
		expectedIndex = 19;
		cut = new HourHand(numberOfLED);
		cut.setHour(hour, minute);
		assertEquals(expectedIndex, cut.getIndex());
	}
	
	@Test
	void switchBy180Degrees() {
		int numberOfLED = 120;
		int hour = 21;
		int minute = 0;
		int expectedIndex = 90;
		HourHand cut = new HourHand(numberOfLED);
		cut.setHour(hour, minute);
		assertEquals(expectedIndex, cut.getIndex());
		
		cut.switchBy180Degrees();
		expectedIndex = 30;
		assertEquals(expectedIndex, cut.getIndex());
		
		numberOfLED = 60;
		hour = 13;
		minute = 55;
		expectedIndex = 9;
		cut = new HourHand(numberOfLED);
		cut.setHour(hour, minute);
		assertEquals(expectedIndex, cut.getIndex());
		
		cut.switchBy180Degrees();
		expectedIndex = 39;
		assertEquals(expectedIndex, cut.getIndex());
		
	}


}
