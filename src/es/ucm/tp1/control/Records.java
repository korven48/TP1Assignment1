package es.ucm.tp1.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Records {
	
	private static final String FILENAME = "record"; // .txt
	private Map<String, Long> records;
	
	public Records () {
		// Inializes the record of each available level to the maximum value
		records = new HashMap<String, Long>();
		for (Level level: Level.values()) {
			records.put(level.name(), Long.MAX_VALUE);
		}
		load();
	}
	
	public boolean trySetNewRecord(String levelName, long time) {
		// If the time is lower than previous record for the level, the record is updated
		boolean isRecord = false;
		if (time < records.get(levelName)) {
			records.replace(levelName, time);	
			isRecord = true;
		}
		return isRecord;
	}
	
	public void save() {
		try ( FileWriter file      = new FileWriter(FILENAME + ".txt");
			  BufferedWriter bfile = new BufferedWriter(file)
				){
			bfile.write(this.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void load() {
		try ( FileReader file = new FileReader(FILENAME + ".txt");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		// Format: <level name>: <record>
		StringBuilder str = new StringBuilder();
		for (Map.Entry<String, Long> entry: records.entrySet()) {
			str.append(entry.getKey() + ":" + entry.getValue());
			str.append("\n");
		}
		return str.toString();
	}
}
