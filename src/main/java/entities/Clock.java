package entities;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Consumer;

import fsm.OrdinalState;
import fsm.State;
import fsm.SundialState;
import io.vertx.core.Vertx;

public class Clock {

	LEDStrip ledStrip = new LEDStrip(60);
	private State state = new OrdinalState(ledStrip, this::display);
//	private final Vertx vertx = Vertx.vertx();

	public static void main(String[] args) throws InterruptedException {
		Clock clock = new Clock();
		Scanner scan = new Scanner(System.in);
		clock.state.display();
		while (true) {
			int nextInt = scan.nextInt();
			if (nextInt == 1) {
				while (nextInt != 0) {
					clock.state.reactOnMotion();
					nextInt = scan.nextInt();
				}

			}
			if (nextInt == 9) {
				clock.switchToState(new SundialState(clock.ledStrip, clock::display));
			}
			if (nextInt == 100) {
				break;
			}
			clock.state.display();
		}
		scan.close();
	}

	public void switchToState(State state) {
		this.state = state;
		this.state.start(0);
	}

	public void display(LEDStrip ledstrip) {
		ledstrip.display();
	}
}
