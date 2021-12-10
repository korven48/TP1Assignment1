package es.ucm.tp1.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.ucm.tp1.Exceptions.highlevelexceptions.GameException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.InputOutputRecordException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.RecordsException;

public class Records {
	
	private static final String FILE_NAME = "record"; 
	private static final String FILE_EXTENTION = ".txt";
	private Map<String, Long> records;
	private static final String defaultValues = "";
	
	public Records () throws GameException {
		// Inializes the record of each available level to the maximum value
		records = new HashMap<String, Long>();
		for (Level level: Level.values()) {
			records.put(level.name(), Long.MAX_VALUE);
		}
		try {
			load();
		} catch (InputOutputRecordException ex) {
			throw new GameException(ex.getMessage(), ex);
		} finally {
			//Create the file --> Creating a new record.
		}
	}
	
	public void trySetNewRecord(String levelName, long time) throws RecordsException {
		// If the time is lower than previous record for the level, the record is updated
		if (time < records.get(levelName)) {
			records.replace(levelName, time);	
		} else {
			throw new RecordsException("The new time is no record!");
		}
	}
	
	public void save() {
		try ( FileWriter file      = new FileWriter(Records.FILE_NAME + Records.FILE_EXTENTION);
			  BufferedWriter bfile = new BufferedWriter(file)) {
				bfile.write(this.toString());
		} catch (IOException ex) {
				ex.printStackTrace();
		}
	}
	
	
	public void load() throws InputOutputRecordException {
		try ( FileReader file = new FileReader(FILE_NAME + ".txt");
			  BufferedReader bfile = new BufferedReader(file);){
			
			String levelName, line = bfile.readLine();
			String words[];
			long levelTime;
			// For the records in the file
			while (line != null) {
				// Parse each record
				words = line.trim().split(":");
				levelName = words[0];
				levelTime = Long.parseLong(words[1]);
				
				// Add Record
				records.replace(levelName, levelTime);
				
				line = bfile.readLine();
			}
		} catch (FileNotFoundException ex) {
			throw new InputOutputRecordException("Permissons failure occurred", ex);
		} catch (NumberFormatException ex) {
			throw new InputOutputRecordException("Numerical failure occurred", ex);
		} catch (IOException ex) {
			throw new InputOutputRecordException("Another IO error occurred", ex);
		}
	}
	
	@Override
	public String toString() {
		// Format: <level name>: <record>
		StringBuilder str = new StringBuilder();
		for (Map.Entry<String, Long> entry: records.entrySet()) {
			str.append(entry.getKey() + ":" + entry.getValue());
			str.append(String.format("%n"));
		}
		return str.toString();
	}
}
