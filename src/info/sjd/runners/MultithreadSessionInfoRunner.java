package info.sjd.runners;

import info.sjd.threadClasses.RecorderByIntervals;

public class MultithreadSessionInfoRunner {

	public static void main(String[] args) {

		try {
			for (int i = 0; i < 3; i++) {

				new RecorderByIntervals().start();
				new RecorderByIntervals().start();
				new RecorderByIntervals().start();

				Thread.sleep(180000);
			}

		} catch (InterruptedException e) {

		}

	}

}
