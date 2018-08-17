package info.sjd.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import info.sjd.emulators.SessionInfoGenerator;

public class IOProcessor {


	//WRITE RECORDS TO FILE
	public void writeLogToFile(SessionInfoGenerator sie) {

		char[] buffer = new char[sie.getSessionInfo().length()];
		sie.getSessionInfo().getChars(0, sie.getSessionInfo().length(), buffer, 0);

		try (FileWriter fileWriter = new FileWriter("src/info/sjd/content/log.txt", true)) {

			for (int i = 0; i < buffer.length; i++) {
				fileWriter.write(buffer[i]);
			}

		} catch (IOException e) {

			System.out.println("Output log to file fault");
			e.printStackTrace();

		}

	}

	// READ RECORDS FROM FILE
	public void readLogFromFile(long fromDate, long toDate) {

		Logger logger = Logger.getLogger(IOProcessor.class.getSimpleName());

		try (BufferedReader reader = new BufferedReader(new FileReader("src/info/sjd/content/log.txt"))) {

			String line;

			while ((line = reader.readLine()) != null) {

				long dateOfRecordCreate = Long.parseLong(line.substring(0, 13));
				if (dateOfRecordCreate > fromDate && dateOfRecordCreate < toDate) {

					logger.info(line);

				}
			}
		} catch (IOException e) {

			System.out.println("Reading log from file is fault");
			e.printStackTrace();

		}

	}

	// REMOVE FROM LOG FILE RECORDS OLDER THAN THREE DAYS
	public void deleteOldRecords() {

		long ThreeDays = 259200000;
		long dateOfRecord;
		String line;
		List<String> recordsBufferList = new ArrayList<String>();

		// READ LOG AND FILTER RECORDS BY DATE

		try (BufferedReader reader = new BufferedReader(new FileReader("src/info/sjd/content/log.txt"))) {

			while ((line = reader.readLine()) != null) {

				dateOfRecord = Long.parseLong(line.substring(0, 13));

				if (dateOfRecord > System.currentTimeMillis() - ThreeDays) {
					recordsBufferList.add(line + "\r\n");
				}
			}
		} catch (IOException e) {

			System.out.println("Reading log from file is fault");
			e.printStackTrace();

		}

		// WRITE NEW LOG FILE

		try (FileWriter fileWriter = new FileWriter("src/info/sjd/content/log.txt")) {

			for (int i = 0; i < recordsBufferList.size(); i++) {

				char[] buffer = new char[recordsBufferList.get(i).length()];
				recordsBufferList.get(i).getChars(0, recordsBufferList.get(i).length(), buffer, 0);

				for (int i2 = 0; i2 < buffer.length; i2++) {
					fileWriter.write(buffer[i2]);

				}

			}
		} catch (IOException e) {

			System.out.println("Output log to file fault");
			e.printStackTrace();

		}

	}

}