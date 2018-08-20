package info.sjd.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import info.sjd.emulators.RandomValueGenerator;
import info.sjd.emulators.SessionInfo;
import info.sjd.exceptions.ExceptionSJD;

class IOPracessorTest {
	private SessionInfo sessionInfoOriginal;
	private SessionInfo sessionInfoReconstructed;
	List<SessionInfo> sessionInfoList;

	@Test
	void testWriteLogToFile() {

//Instantiation section
		sessionInfoOriginal = new SessionInfo();

		String ipAddress = new RandomValueGenerator().getRandomValue(0, 255) + "."
				+ new RandomValueGenerator().getRandomValue(0, 255) + "."
				+ new RandomValueGenerator().getRandomValue(0, 255) + "."
				+ new RandomValueGenerator().getRandomValue(0, 255);

		sessionInfoOriginal.setSessionTime(System.currentTimeMillis());
		sessionInfoOriginal.setSessionID(new RandomValueGenerator().getRandomValue(100000000, 999999999));
		sessionInfoOriginal.setIpAddress(ipAddress);

// Write section
		try {
			new IOProcessor().writeLogToFile(sessionInfoOriginal);
		} catch (ExceptionSJD e) {
			e.printStackTrace();
		}

// Read section
		try {

			sessionInfoList = new IOProcessor().readLogPerPeriod(sessionInfoOriginal.getSessionTime(),
					sessionInfoOriginal.getSessionTime());
		} catch (ExceptionSJD e) {
			e.printStackTrace();
		}

		sessionInfoReconstructed = sessionInfoList.get(0);

//Assertion section
		assertEquals(sessionInfoOriginal.toString(), sessionInfoReconstructed.toString());

	}
	


}
