package info.sjd.emulators;

import java.util.Random;

public class SessionInfoGenerator {

	private StringBuilder sessionInfo;
	private int sessionID;
	private long sessionTime;
	private String ipAddress;

	public SessionInfoGenerator() {

		setSessionTime();
		setRandomSessionID();
		setRandomIPAddress();
		setInfoString();
	}

	private void setRandomSessionID() {
		int temp = new Random().nextInt(999999999);
		if (temp >= 100000000) {
			sessionID = temp;
		} else {
			setRandomSessionID();
		}
	}

	private void setSessionTime() {
		sessionTime = java.lang.System.currentTimeMillis();
	}

	private void setRandomIPAddress() {
		int ipBlock1 = new Random().nextInt(255);
		int ipBlock2 = new Random().nextInt(255);
		int ipBlock3 = new Random().nextInt(255);
		int ipBlock4 = new Random().nextInt(255);
		ipAddress = ipBlock1 + "." + ipBlock2 + "." + ipBlock3 + "." + ipBlock4;
	}

	private void setInfoString() {

		sessionInfo = new StringBuilder().append(sessionTime).append(" ").append(sessionID).append(" ")
				.append(ipAddress).append("\r\n");
	}

	public String getSessionInfo() {

		return sessionInfo.toString();
	}

}
