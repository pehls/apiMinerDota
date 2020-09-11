
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import parser.JSONFlattener;
import utils.Constants;

public class JSONretriever {

	public static void main(String[] args) throws Exception {

		saveLoad();
		for (int hero_id = 0; hero_id <= 114; hero_id++) {

			extractJSONFrom(hero_id);
		}
	}

	public static boolean execAll () throws Exception {
		saveLoad();
		boolean retorno = false;
		for (int hero_id = 0; hero_id<=114; hero_id++) {
			File file = new File(Constants.FILES_BY_HERO +"\\00-"+hero_id+"\\"+hero_id+ Constants.JSON);
			extractJSONFrom(hero_id);
			if (file.exists()) retorno = true;
		}
	
		return retorno;
	}

	public static boolean extractJSONFrom(int hero_id) throws Exception {
		String url = Constants.API_STEAMPOWERED_IDOTA2_MATCH_570 + Constants.GET_MATCH_HISTORY + "hero_id=" + hero_id
				+ "&" + Constants.KEY;
		saveJSON(JSONFlattener.parseJson(new URI(url)), hero_id, getJSONFromURL(url));
		File file = new File(Constants.FILES_BY_HERO + "\\00-" + hero_id + "\\" + hero_id + Constants.JSON);
		if (file.exists())
			return true;
		return false;
	}

	/**
	 * this class returns the json parsed from a URL
	 * 
	 * @param url
	 *            the url to parse
	 * @return a json parsed
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static String getJSONFromURL(String url) throws IOException, URISyntaxException {
		String json = IOUtils.toString(new URI(url), "UTF-8");
		return json;
	}

	/**
	 * This class save a JSON to a file
	 * 
	 * @param flatJson
	 *            the archive link to write
	 * @param hero_id
	 *            hero_id used to the destiny file
	 * @param json
	 *            the json to write in a file
	 * @throws IOException
	 */
	private static void saveJSON(List<Map<String, String>> flatJson, int hero_id, String json) throws IOException {
		System.out.println("salvando json hero " + hero_id);
		FileUtils.write(new File(Constants.FILES_BY_HERO + "\\00-" + hero_id + "\\" + getNumberOfFiles() + "-" + hero_id
				+ Constants.JSON), json, "ISO8859_1");

	}

	private static void saveLoad() throws IOException {
		int numFiles = getNumberOfFiles() + 1;
		FileWriter arq = new FileWriter("../apiMinerDota/src/utils/properties");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.printf("numFiles=" + numFiles);

		arq.close();
	}

	private static int getNumberOfFiles() {
		return readFile("../apiMinerDota/src/utils/properties");
	}

	private static int readFile(String file) {
		try {
			FileReader arq = new FileReader(file);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			while (linha != null) {
				String[] prop = linha.split("=");
				if (prop[0].equalsIgnoreCase("numFiles"))
					return Integer.parseInt(prop[1]);
				linha = lerArq.readLine(); // lê da segunda até a última linha
			}

			arq.close();
		} catch (Exception e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return 0;
	}

}
