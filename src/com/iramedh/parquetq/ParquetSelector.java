package com.iramedh.parquetq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParquetSelector {

	public static void main(String[] args) {
		
		// String srchField="id";
		// String srchValue="1000";
		//String dataFile = "inputs/data.txt";

		String srchField = args[0];
		String srchValue = args[1];
		String dataFile=args[2];
		Integer cnt = 0;

		try {
			// Read the data file
			FileReader reader = new FileReader(dataFile);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				// Parse each line JSONParser

				try {
					JSONParser dataFileJsonParser = new JSONParser();

					Object dataFileObject = dataFileJsonParser.parse(line);

					JSONObject dataFileJSONObject = (JSONObject) dataFileObject;

					if (srchValue.equals("blank")) {
						if (dataFileJSONObject.get(srchField).toString().length() == 0) {
							System.out.println(line);
							cnt++;
						}
					} else if (srchValue.equals("notblank")) {
						if (dataFileJSONObject.get(srchField).toString().length() > 0) {
							System.out.println(line);
							cnt++;
						}
					} else if (dataFileJSONObject.get(srchField).toString().equals(srchValue.toString())) {
						System.out.println(line);
						cnt++;
					} 
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			System.out.println("\nTotal Record count is " + cnt);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
