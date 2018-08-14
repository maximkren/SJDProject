package info.sjd.runners;

import info.sjd.emulators.SessionInfoEmulator;

public class SessionInfoRunner {

	public static void main(String[] args) {
		SessionInfoEmulator sie = new SessionInfoEmulator();
		
		
		
		System.out.println(sie.getSessionInfo());
	}

}
