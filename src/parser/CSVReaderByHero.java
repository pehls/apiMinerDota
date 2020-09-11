package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import utils.Constants;
import writer.CSVWriter;


public class CSVReaderByHero {

	public static void main(String[] args) throws Exception {
		//for (int i = 1; i<=4; i++)
			read(Constants.FILES_BY_HERO + "00-"+4+"/", 4);
	}

	public static void read(String csvFile, int i) throws Exception {

		BufferedReader br = null;
		String line = "";

		try {
			for (int j = 0; j <= 4; j++) {
				br = new BufferedReader(new FileReader(csvFile+"0"+j+"-"+i+Constants.CSV));
				while ((line = br.readLine()) != null) {
					
					// use comma as separator
					String[] data = line.split(Constants.CVS_SPLIT_BY);
					System.out.println("match_id = " + data[2]);
//					extractCSVFrom("https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?match_id="
//							+ data[2] + "&key=92D86E28A272B9D6BDAC5E0DCB00FEA5",i, j);
					if (data[2].compareToIgnoreCase("result.matches[100].match_id")!=0) 
						extractCSVFrom (Constants.HTTPS_API_OPENDOTA_COM_API + "/matches/" +data[2],i,j, data[2]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Parse JSON from URL and convert it to CSV
	 * 
	 * @param url
	 *            String, URL to parse
	 * @param j
	 *            int local
	 * @param data 
	 */
	private static void extractCSVFrom(String url, int i, int j, String data) throws Exception {
		String json = IOUtils.toString(new URI(url), "UTF-8");
		FileUtils.write(new File(Constants.FILES_OUT_BY_HERO + "test/00-" +i+"/"+data+  Constants.JSON), json, "ISO8859_1", true);
		parseJSONtoCSV(JSONFlattener.parseJson(new URI(url)), i,j, json);
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
	private static void parseJSONtoCSV(List<Map<String, String>> flatJson, int i, int j, String json) throws IOException {
		FileUtils.write(new File(Constants.FILES_OUT_BY_HERO + "test/00-" +i+"/0"+j+"-"+i+  Constants.JSON), json, "ISO8859_1", true);
		
		// flatJson = JSONFlattener.parseJson(new
		// File("files/sample_large.json"), "UTF-8");
		// Using ';' as separator
		Set<String> header = CSVWriter.collectHeaders(flatJson);
		// the intention is generate a csv file with specific headers - not all
//		if (j == 1)
//			CSVWriter.writeLargeFile(flatJson, ",", "files/out/byHero/test/00-"+i+"/0"+j+"-"+i+".csv", header);
//		else
			CSVWriter.writeLargeFileWithoutHeader(flatJson, Constants.CVS_SPLIT_BY, Constants.FILES_OUT_BY_HERO + "test/00-" +i+"/0"+j+"-"+i+ Constants.CSV, header);
	}

}
