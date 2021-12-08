package es.ucm.tp1.control;

import java.util.HashMap;
import java.util.Map;

public class Records {
	
	private Map<String, Long> records;
	
	public Records () {
		// Inializes the record of each available level to the maximum value
		records = new HashMap<String, Long>();
		for (Level level: Level.values()) {
			records.put(level.name(), Long.MAX_VALUE);
		}
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
	
	@Override
	public String toString() {
		// Format: <level name>: <record>
		StringBuilder str = new StringBuilder();
		for (Map.Entry<String, Long> entry: records.entrySet()) {
			str.append(entry.getKey() + ": " + entry.getValue());
			str.append("\n");
		}
		return str.toString();
	}
}
