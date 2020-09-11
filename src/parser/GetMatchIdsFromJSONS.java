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



public class GetMatchIdsFromJSONS {
	private String wdForRFunction = "C:\\Users\\gabri\\git\\APIDotaMiner\\apiMinerDota\\files\\byHero\\jsons\\";
	public static void main(String[] args) throws Exception {
		//for (int i = 1; i<=4; i++)
			read(Constants.FILES_BY_HERO + "00-"+4+"/", 4);
	}
	private static void getMatchIdsFromJSONS() {
		//deve dar run em uma classe no R, formando o json com info do match id, a ser lida pela classe abaixo
	}
	/**
	 * Read csvFile with match_ids to parse and save it
	 * @param csvFile
	 * 				csvFile with match_ids
	 * @param hero_id
	 * 				hero_id to catch matches
	 * @throws Exception
	 * 				IOUtils exception
	 */
	public static void read(String csvFile, int hero_id) throws Exception {

		BufferedReader br = null;
		String line = "";

		try {
			for (int j = 0; j <= 4; j++) {
				br = new BufferedReader(new FileReader(csvFile+"0"+j+"-"+hero_id+Constants.CSV));
				while ((line = br.readLine()) != null) {
					
					// use comma as separator
					String[] match_ids = line.split(Constants.CVS_SPLIT_BY);
					System.out.println("match_id = " + match_ids[2]);
//					extractCSVFrom("https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?match_id="
//							+ data[2] + "&key=92D86E28A272B9D6BDAC5E0DCB00FEA5",i, j);
					if (match_ids[2].compareToIgnoreCase("result.matches[100].match_id")!=0) 
						saveJSONfrom(Constants.HTTPS_API_OPENDOTA_COM_API + "/matches/" ,hero_id, match_ids[2]);
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
	 * Parse JSON from URL and save it
	 * 
	 * @param url
	 *				String, URL to parse
	 * @param hero_id
	 *				hero_id to catch
	 * @param match_id 
	 *				match_id from matches
	 */
	private static void saveJSONfrom(String url, int hero_id, String match_id) throws Exception {
		String json = IOUtils.toString(new URI(url+match_id), "UTF-8");
		FileUtils.write(new File(Constants.FILES_OUT_BY_HERO + "test/00-" +hero_id+"/"+match_id+  Constants.JSON), json, "ISO8859_1", true);
	}

}
