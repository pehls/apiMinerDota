/*
 * Copyright 2012-2014 Dristhi software
 * Copyright 2015 Arkni Brahim
 * Copyright 2017 Gabriel Pehls
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import parser.JSONFlattener;
import utils.Constants;
import writer.CSVWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		extractCSVFrom(
				Constants.API_STEAMPOWERED_IDOTA2_MATCH_570 + Constants.GET_MATCH_HISTORY + Constants.KEY,
				23);
	}

	private static void extractCSVFrom(String url, int to) throws Exception {
		String json = IOUtils.toString(new URI(url), "UTF-8");
		parseJSONtoCSV(JSONFlattener.parseJson(new URI(url)), to, json);
		// Using ';' as separator
		// CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, ";"),
		// "files/sample_uri.csv");
	}

	/**
	 * Parse a Large JSON File and convert it to CSV
	 * 
	 * @param flatJson
	 *            the json to parse
	 * @throws IOException 
	 */
	private static void parseJSONtoCSV(List<Map<String, String>> flatJson, int to, String json) throws IOException {
		FileUtils.write(new File(Constants.FILES_MATCHES + "jsons/" +to+Constants.JSON), json, "ISO8859_1");
		// flatJson = JSONFlattener.parseJson(new
		// File("files/sample_large.json"), "UTF-8");
		// Using ';' as separator
		Set<String> header = CSVWriter.collectHeaders(flatJson);
		// the intention is generate a csv file with specific headers - not all
		CSVWriter.writeLargeFile(flatJson, ",", Constants.FILES_MATCHES + to + Constants.CSV, header);
		System.out.println(to + " OK -------------------------------- ");
	}

}
