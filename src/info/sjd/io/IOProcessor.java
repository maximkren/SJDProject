package info.sjd.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.sjd.emulators.SessionInfo;
import info.sjd.exceptions.ExceptionSJD;

public class IOProcessor {
	String logFile = "src/info/sjd/content/log.txt";

	// PUT RECORDS TO FILE
	public void writeLogToFile(SessionInfo sie) throws ExceptionSJD {
		if (sie == null) {
			throw new ExceptionSJD("SessionInfo object is not initialized");
		} else {
			try (FileWriter fileWriter = new FileWriter(logFile, true)) {

				fileWriter.write(sie.toString());
				fileWriter.write("\r\n");
				fileWriter.flush();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
	}

	// GET RECORDS FROM FILE. return LIST of SessioInfo objects
	public List<SessionInfo> readLogPerPeriod(long fromDate, long toDate) throws ExceptionSJD {

		List<SessionInfo> listInRange = new ArrayList<SessionInfo>();

		if (fromDate <= 0 || fromDate > toDate || toDate <= 0 || toDate < fromDate) {
			throw new ExceptionSJD("incorrect period parameters");
		} else {

			try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {

				String line;

				while ((line = reader.readLine()) != null) {

					long dateOfRecordCreate = Long.parseLong(line.substring(0, 13));

					if (dateOfRecordCreate > fromDate && dateOfRecordCreate < toDate) {

						SessionInfo sessionInfo = new SessionInfo();
						String[] fieldsSessionInfo = line.split(" ");

						sessionInfo.setSessionTime(Long.valueOf(fieldsSessionInfo[0]));
						sessionInfo.setSessionID(Integer.valueOf(fieldsSessionInfo[1]));
						sessionInfo.setIpAddress(fieldsSessionInfo[2]);

						listInRange.add(sessionInfo);
					}
				}
			} catch (IOException e) {

				System.out.println("Reading log from file is fault");
				e.printStackTrace();

			}
		}

		return listInRange;
	}

	// DELETE FROM LOG FILE RECORDS OLDER THAN SPECIFIED PERIOD OF TIME
	public void deleteOldRecords(long olderThan) throws ExceptionSJD {

		if (olderThan <= 0 || olderThan > System.currentTimeMillis()) {
			throw new ExceptionSJD("incorrect 'older than' parameter");
		} else {

			List<SessionInfo> filteredList = readLogPerPeriod(System.currentTimeMillis() - olderThan,
					System.currentTimeMillis());

			try (FileWriter fileWriter = new FileWriter(logFile)) {

				for (int i = 0; i < filteredList.size(); i++) {
					fileWriter.write(filteredList.get(i).toString());
					fileWriter.write("\r\n");
					fileWriter.flush();
				}

			} catch (IOException e) {

				System.out.println("Output log to file fault");
				e.printStackTrace();

			}

		}
	}

}