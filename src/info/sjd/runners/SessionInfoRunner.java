package info.sjd.runners;

import java.util.ArrayList;
import java.util.List;

import info.sjd.emulators.SessionInfoGenerator;
import info.sjd.io.IOProcessor;

public class SessionInfoRunner {

	public static void main(String[] args) {
		List<SessionInfoGenerator> sieList = new ArrayList<SessionInfoGenerator>();
		IOProcessor ioProvider = new IOProcessor();

		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());
		sieList.add(new SessionInfoGenerator());

		for (int i = 0; i < sieList.size(); i++) {
			ioProvider.writeLogToFile(sieList.get(i));

		}

		// ioProvider.deleteOldEntries();

		ioProvider.readLogFromFile(System.currentTimeMillis() - 7000, System.currentTimeMillis());

	}

}
