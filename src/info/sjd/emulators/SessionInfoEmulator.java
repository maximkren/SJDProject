package info.sjd.emulators;

import java.util.Random;

public class SessionInfoEmulator {

	private StringBuilder sessionInfo;
	private int sessionID;
	private long sessionTime;
	private String ipAddress;

	public SessionInfoEmulator() {

		setSessionTime();
		setRandomSessionID();
		setRandomIPAddress();
		setInfoString();
	}

	private void setRandomSessionID() {
		int temp = new Random().nextInt(999999999);
		if (temp >= 100000000) {
			sessionID = new Random().nextInt(999999999);
		} else {
			setRandomSessionID();
		}
	}

	private void setSessionTime() {
		sessionTime = java.lang.System.currentTimeMillis();
	}

	private void setRandomIPAddress() {
		int a = new Random().nextInt(255);
		int b = new Random().nextInt(255);
		int c = new Random().nextInt(255);
		int d = new Random().nextInt(255);
		ipAddress = a + "." + b + "." + c + "." + d;
	}

	private void setInfoString() {

		sessionInfo = new StringBuilder().append(sessionTime).append(" ").append(sessionID).append(" ")
				.append(ipAddress);
	}

	public String getSessionInfo() {

		return sessionInfo.toString();
	}

}
