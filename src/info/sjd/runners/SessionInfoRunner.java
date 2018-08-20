package info.sjd.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import info.sjd.emulators.RandomValueGenerator;
import info.sjd.emulators.SessionInfo;
import info.sjd.exceptions.ExceptionSJD;
import info.sjd.io.IOProcessor;

public class SessionInfoRunner {

	private static Logger logger = Logger.getLogger(SessionInfoRunner.class.getSimpleName());

	public static void main(String[] args) {
		RandomValueGenerator r = new RandomValueGenerator();
		SessionInfo sessionInfo;
		List<SessionInfo> sieList = new ArrayList<SessionInfo>();
		IOProcessor ioProcessor = new IOProcessor();

		// create collection with SessionInfo objects
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

		// get records for a certain period of time
		try {

			List<SessionInfo> sieList1 = ioProcessor.readLogPerPeriod(System.currentTimeMillis() - 6000,
					System.currentTimeMillis());
			for (int i = 0; i < sieList1.size(); i++) {
				logger.info(sieList1.get(i).toString());
			}

		} catch (ExceptionSJD e) {
			e.printStackTrace();
		}

		// delete records older than the specified period of time
		try {

			ioProcessor.deleteOldRecords(86400000);
			

		} catch (ExceptionSJD e) {
			e.printStackTrace();
		}
	}

}
