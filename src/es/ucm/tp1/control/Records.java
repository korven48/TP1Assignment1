package es.ucm.tp1.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.ucm.tp1.Exceptions.lowlevelexceptions.InputOutputRecordException;
import es.ucm.tp1.Exceptions.lowlevelexceptions.RecordsException;
import es.ucm.tp1.model.Game;

public class Records {
	
	private static final String FILE_NAME = "record"; 
	private static final String FILE_EXTENTION = ".txt";
	private Map<String, Long> records;
	private Game game = null;
	private boolean isRecord = false;
	
	//For error Messages
	private static final String ERROR_FILE_NOT_FOUND = "File not found, created new record-file";
	private static final String ERROR_WHILE_SAVING = "And saving error occured";
	private static final String ERROR_NUMERICAL = "Numerical failure occurred";
	private static final String ERROR_Other = "Another IO error occurred";
	
	
	public Records (Game game) {
		// Inializes the record of each available level to the maximum value
		this.game = game;
		records = new HashMap<String, Long>();
		for (Level level: Level.values()) {
			records.put(level.name(), Long.MAX_VALUE);
		}
	}
	
	public void trySetNewRecord(String levelName, long time) throws RecordsException {
		// If the time is lower than previous record for the level, the record is updated
		if (time < records.get(levelName)) {
			records.replace(levelName, time);
			this.isRecord = true;
		} else {
			throw new RecordsException("The new time is no record!");
		}
	}
	
	public boolean getIsRecord() {
		return this.isRecord;
	}
	
	public void save() throws InputOutputRecordException {
		try ( FileWriter file      = new FileWriter(Records.FILE_NAME + Records.FILE_EXTENTION);
			  BufferedWriter bfile = new BufferedWriter(file)) {
				bfile.write(this.toString());
		} catch (IOException ex) {
				throw new InputOutputRecordException(Records.ERROR_WHILE_SAVING, ex);
		}
	}
	
	
	public void load() throws InputOutputRecordException {
		try ( FileReader file = new FileReader(FILE_NAME + FILE_EXTENTION);
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
			this.save();
			throw new InputOutputRecordException(Records.ERROR_FILE_NOT_FOUND, ex);
		} catch (NumberFormatException ex) {
			this.save();
			throw new InputOutputRecordException(Records.ERROR_NUMERICAL, ex);
		} catch (IOException ex) {
			game.setExit(true);
			throw new InputOutputRecordException(Records.ERROR_Other, ex);
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
	
	private long getLevelRecords(String levelName) {
		long value = this.records.get(levelName);
		return value;
	}
	
	public String getLevelRecords(Level level) {
		double seconds = this.getLevelRecords(level.name()) / 1000 ;
		String time = "Hello Player, the record for " + level.name() + " is: " + String.format("%.2f", seconds) + "s" + String.format("%n");
		return time;
	}
}
