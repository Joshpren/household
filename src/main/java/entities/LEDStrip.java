package entities;

import java.util.stream.IntStream;

public class LEDStrip {

	/**
	 * Displays the time via LED-Diode. Depends on mode.
	 */
	private final Pixel[] pixels;

	public LEDStrip(int numberOfPixel) {
		this.pixels = IntStream.range(0,numberOfPixel)
				.mapToObj(index -> new Pixel(index))
				.toArray(Pixel[]::new);
	}

	public int getLength() {
		return pixels.length;
	}

	public void setHand(Hand hand) {
		int indexOfHand = hand.getIndex();
		pixels[indexOfHand].setConfiguration(hand);
	}

	public void clear() {
		for (Pixel pixel : pixels) {
			pixel.clear();
		}
	}

	public void display() {
		for (Pixel pixel : pixels) {
			System.out.println(pixel);
		}
	}
	
	public void setAmbient(float brightness) {
		for (Pixel pixel : pixels) {
			pixel.setWhite(brightness);
		}
	}

}
