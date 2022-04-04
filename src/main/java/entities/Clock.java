package entities;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Consumer;

import fsm.OrdinalState;
import fsm.State;
import fsm.SundialState;
import fsm.TimerState;
import io.vertx.core.Vertx;
import lombok.Getter;
import lombok.Setter;

public class Clock {

	private final LEDStrip ledStrip = new LEDStrip(120);
	private final Vertx vertx = Vertx.vertx();
	@Setter
	@Getter
	private long currentTimer = -1;
	private State state = new OrdinalState(vertx, this::setCurrentTimer, ledStrip, this::display);

	public static void main(String[] args) throws InterruptedException {
		Clock clock = new Clock();
		Scanner scan = new Scanner(System.in);
		clock.state.start();
		while (true) {
			int nextInt = scan.nextInt();
			if (nextInt == 1) {
				clock.state.reactOnMotion();
				while (nextInt != 0) {
					nextInt = scan.nextInt();
				}
				clock.vertx.cancelTimer(clock.currentTimer);
			}
			if (nextInt == 7) {
				clock.vertx.cancelTimer(clock.currentTimer);
				int hours  = scan.nextInt();
				int minutes  = scan.nextInt();
				int seconds  = scan.nextInt();
				clock.switchToState(new TimerState(clock.vertx,clock::setCurrentTimer, clock.ledStrip, clock::display, hours, minutes, seconds));
			}
			if (nextInt == 8) {
				clock.vertx.cancelTimer(clock.currentTimer);
				clock.switchToState(new OrdinalState(clock.vertx,clock::setCurrentTimer, clock.ledStrip, clock::display));
			}
			if (nextInt == 9) {
				clock.vertx.cancelTimer(clock.currentTimer);
				clock.switchToState(new SundialState(clock.vertx,clock::setCurrentTimer, clock.ledStrip, clock::display));
			}
			if (nextInt == 100) {
				break;
			}
		}
		scan.close();
		clock.vertx.close();
	}

	public void switchToState(State state) {
		this.state = state;
		this.state.start();
	}

	public void display(LEDStrip ledstrip) {
		ledstrip.display();
	}
}
