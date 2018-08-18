package info.sjd.threadClasses;

import java.util.ArrayList;
import java.util.List;

import info.sjd.emulators.RandomValueGenerator;
import info.sjd.emulators.SessionInfo;
import info.sjd.exceptions.ExceptionSJD;
import info.sjd.io.IOProcessor;

public class RecorderByIntervals extends Thread {

	
	@Override
	public synchronized void run() {

		
		RandomValueGenerator r = new RandomValueGenerator();
		SessionInfo sessionInfo;
		List<SessionInfo> sieList = new ArrayList<SessionInfo>();
		IOProcessor ioProcessor = new IOProcessor();

		// create collection with 10 SessionInfo objects
		for (int i = 0; i < 10; i++) {

			sessionInfo = new SessionInfo();

			String ipAddress = r.getRandomValue(0, 255) + "." + r.getRandomValue(0, 255) + "."
					+ r.getRandomValue(0, 255) + "." + r.getRandomValue(0, 255);

			sessionInfo.setSessionTime(System.currentTimeMillis());

			sessionInfo.setSessionID(r.getRandomValue(100000000, 999999999));

			sessionInfo.setIpAddress(ipAddress);

			sieList.add(sessionInfo);
		}

		// push each element of collection into log file
		try {

			for (int i = 0; i < sieList.size(); i++) {
				ioProcessor.writeLogToFile(sieList.get(i));
			}

		} catch (ExceptionSJD e) {
			e.printStackTrace();
		}
		
	}

}
