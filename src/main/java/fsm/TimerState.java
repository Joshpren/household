package fsm;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import entities.LEDStrip;
import entities.Pixel;
import io.vertx.core.Vertx;

public class TimerState extends State {

	private long overallTimeInMilliSeconds;
	private long durationOfIntervall;

	public TimerState(Vertx vertx, Consumer<Long> currentTimer, LEDStrip ledStrip, Consumer<LEDStrip> change, int hours,
			int minutes, int seconds) {
		super(vertx, currentTimer, ledStrip, change);
		this.overallTimeInMilliSeconds = ((hours * 3600) + (minutes * 60) + seconds) * 1000;
		this.durationOfIntervall = overallTimeInMilliSeconds / getLedStrip().getLength();
	}

	@Override
	public void start() {
		calculatePositions(overallTimeInMilliSeconds);
	}

	@Override
	public void display() {
		getChange().accept(getLedStrip());
	}

	public void calculatePositions(long remainingDuration) {
		if(remainingDuration <= 0) {
			return;
		}
		double percentage = (double) remainingDuration / (double) overallTimeInMilliSeconds;
		int tillIndex = (int) ((double) getLedStrip().getLength() * percentage);
		System.out.println(tillIndex);
		getLedStrip().lightUpTill(tillIndex);
		display();
		setCurrentTimer(getVertx().setTimer(durationOfIntervall, ignored -> calculatePositions(remainingDuration-durationOfIntervall)));
	}

	@Override
	public void reactOnMotion() {
		// TODO Auto-generated method stub
	}

}
