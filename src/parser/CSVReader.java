package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import utils.Constants;
import writer.CSVWriter;
//HEADER
// 	first_blood_time,cluster,match_seq_num,human_players,barracks_status_radiant,duration,patch,radiant_win,engine,skill,negative_votes,tower_status_radiant,players[1].cluster,players[1].gold_spent,players[1].patch,players[1].deaths,players[1].gold_per_min,players[1].lose,players[1].kda,players[1].gold,players[1].radiant_win,players[1].match_id,players[1].leaver_status,players[1].hero_healing,players[1].game_mode,players[1].xp_per_min,players[1].benchmarks.gold_per_min.pct,players[1].benchmarks.gold_per_min.raw,players[1].benchmarks.tower_damage.pct,players[1].benchmarks.tower_damage.raw,players[1].benchmarks.xp_per_min.pct,players[1].benchmarks.xp_per_min.raw,players[1].level,players[1].abandons,players[1].player_slot,players[1].start_time,players[1].last_hits,players[1].item_5,players[1].item_4,players[1].item_3,players[1].item_2,players[1].item_1,players[1].item_0,players[1].region,players[1].kills,players[1].hero_damage,players[1].backpack_1,players[1].backpack_2,players[1].denies,players[1].backpack_0,players[1].duration,players[1].assists,players[1].tower_damage,players[1].isRadiant,players[1].hero_id,players[1].win,players[1].lobby_type,players[2].cluster,players[2].gold_spent,players[2].patch,players[2].deaths,players[2].gold_per_min,players[2].lose,players[2].kda,players[2].gold,players[2].radiant_win,players[2].match_id,players[2].leaver_status,players[2].hero_healing,players[2].game_mode,players[2].xp_per_min,players[2].benchmarks.gold_per_min.pct,players[2].benchmarks.gold_per_min.raw,players[2].benchmarks.tower_damage.pct,players[2].benchmarks.tower_damage.raw,players[2].benchmarks.xp_per_min.pct,players[2].benchmarks.xp_per_min.raw,players[2].level,players[2].abandons,players[2].player_slot,players[2].start_time,players[2].last_hits,players[2].item_5,players[2].item_4,players[2].item_3,players[2].item_2,players[2].item_1,players[2].item_0,players[2].region,players[2].kills,players[2].hero_damage,players[2].backpack_1,players[2].backpack_2,players[2].denies,players[2].backpack_0,players[2].duration,players[2].assists,players[2].tower_damage,players[2].isRadiant,players[2].hero_id,players[2].win,players[2].lobby_type,players[3].cluster,players[3].gold_spent,players[3].patch,players[3].deaths,players[3].gold_per_min,players[3].lose,players[3].kda,players[3].gold,players[3].radiant_win,players[3].match_id,players[3].leaver_status,players[3].hero_healing,players[3].game_mode,players[3].xp_per_min,players[3].benchmarks.gold_per_min.pct,players[3].benchmarks.gold_per_min.raw,players[3].benchmarks.tower_damage.pct,players[3].benchmarks.tower_damage.raw,players[3].benchmarks.xp_per_min.pct,players[3].benchmarks.xp_per_min.raw,players[3].level,players[3].abandons,players[3].player_slot,players[3].start_time,players[3].last_hits,players[3].item_5,players[3].item_4,players[3].item_3,players[3].item_2,players[3].item_1,players[3].item_0,players[3].region,players[3].kills,players[3].hero_damage,players[3].backpack_1,players[3].backpack_2,players[3].denies,players[3].backpack_0,players[3].duration,players[3].assists,players[3].tower_damage,players[3].isRadiant,players[3].hero_id,players[3].win,players[3].lobby_type,players[4].cluster,players[4].gold_spent,players[4].patch,players[4].deaths,players[4].gold_per_min,players[4].lose,players[4].kda,players[4].gold,players[4].radiant_win,players[4].match_id,players[4].leaver_status,players[4].hero_healing,players[4].game_mode,players[4].xp_per_min,players[4].benchmarks.gold_per_min.pct,players[4].benchmarks.gold_per_min.raw,players[4].benchmarks.tower_damage.pct,players[4].benchmarks.tower_damage.raw,players[4].benchmarks.xp_per_min.pct,players[4].benchmarks.xp_per_min.raw,players[4].level,players[4].abandons,players[4].player_slot,players[4].start_time,players[4].last_hits,players[4].item_5,players[4].item_4,players[4].item_3,players[4].item_2,players[4].item_1,players[4].item_0,players[4].region,players[4].kills,players[4].hero_damage,players[4].backpack_1,players[4].backpack_2,players[4].denies,players[4].backpack_0,players[4].duration,players[4].assists,players[4].tower_damage,players[4].isRadiant,players[4].hero_id,players[4].win,players[4].lobby_type,players[5].cluster,players[5].gold_spent,players[5].patch,players[5].deaths,players[5].gold_per_min,players[5].lose,players[5].kda,players[5].gold,players[5].radiant_win,players[5].match_id,players[5].leaver_status,players[5].hero_healing,players[5].game_mode,players[5].xp_per_min,players[5].benchmarks.gold_per_min.pct,players[5].benchmarks.gold_per_min.raw,players[5].benchmarks.tower_damage.pct,players[5].benchmarks.tower_damage.raw,players[5].benchmarks.xp_per_min.pct,players[5].benchmarks.xp_per_min.raw,players[5].level,players[5].abandons,players[5].player_slot,players[5].start_time,players[5].last_hits,players[5].item_5,players[5].item_4,players[5].item_3,players[5].item_2,players[5].item_1,players[5].item_0,players[5].region,players[5].kills,players[5].hero_damage,players[5].backpack_1,players[5].backpack_2,players[5].denies,players[5].backpack_0,players[5].duration,players[5].assists,players[5].tower_damage,players[5].isRadiant,players[5].hero_id,players[5].win,players[5].lobby_type,players[6].cluster,players[6].gold_spent,players[6].patch,players[6].deaths,players[6].gold_per_min,players[6].lose,players[6].kda,players[6].gold,players[6].radiant_win,players[6].match_id,players[6].account_id,players[6].leaver_status,players[6].hero_healing,players[6].game_mode,players[6].personaname,players[6].xp_per_min,players[6].benchmarks.gold_per_min.pct,players[6].benchmarks.gold_per_min.raw,players[6].benchmarks.tower_damage.pct,players[6].benchmarks.tower_damage.raw,players[6].benchmarks.xp_per_min.pct,players[6].benchmarks.xp_per_min.raw,players[6].level,players[6].abandons,players[6].player_slot,players[6].start_time,players[6].last_hits,players[6].item_5,players[6].item_4,players[6].item_3,players[6].item_2,players[6].item_1,players[6].item_0,players[6].region,players[6].kills,players[6].hero_damage,players[6].backpack_1,players[6].backpack_2,players[6].denies,players[6].backpack_0,players[6].duration,players[6].assists,players[6].tower_damage,players[6].isRadiant,players[6].hero_id,players[6].win,players[6].lobby_type,players[7].cluster,players[7].gold_spent,players[7].patch,players[7].deaths,players[7].gold_per_min,players[7].lose,players[7].kda,players[7].gold,players[7].radiant_win,players[7].match_id,players[7].account_id,players[7].leaver_status,players[7].hero_healing,players[7].game_mode,players[7].personaname,players[7].xp_per_min,players[7].benchmarks.gold_per_min.pct,players[7].benchmarks.gold_per_min.raw,players[7].benchmarks.tower_damage.pct,players[7].benchmarks.tower_damage.raw,players[7].benchmarks.hero_damage_per_min.pct,players[7].benchmarks.xp_per_min.pct,players[7].benchmarks.xp_per_min.raw,players[7].level,players[7].abandons,players[7].player_slot,players[7].start_time,players[7].last_hits,players[7].item_5,players[7].item_4,players[7].item_3,players[7].item_2,players[7].item_1,players[7].item_0,players[7].region,players[7].kills,players[7].hero_damage,players[7].backpack_1,players[7].backpack_2,players[7].denies,players[7].backpack_0,players[7].duration,players[7].assists,players[7].tower_damage,players[7].isRadiant,players[7].hero_id,players[7].win,players[7].lobby_type,players[7].ability_upgrades_arr[1],players[8].cluster,players[8].gold_spent,players[8].patch,players[8].deaths,players[8].gold_per_min,players[8].lose,players[8].kda,players[8].gold,players[8].radiant_win,players[8].match_id,players[8].leaver_status,players[8].hero_healing,players[8].game_mode,players[8].xp_per_min,players[8].benchmarks.gold_per_min.pct,players[8].benchmarks.gold_per_min.raw,players[8].benchmarks.tower_damage.pct,players[8].benchmarks.tower_damage.raw,players[8].benchmarks.last_hits_per_min.pct,players[8].benchmarks.hero_damage_per_min.pct,players[8].benchmarks.xp_per_min.pct,players[8].benchmarks.xp_per_min.raw,players[8].level,players[8].abandons,players[8].player_slot,players[8].start_time,players[8].last_hits,players[8].item_5,players[8].item_4,players[8].item_3,players[8].item_2,players[8].item_1,players[8].item_0,players[8].region,players[8].kills,players[8].hero_damage,players[8].backpack_1,players[8].backpack_2,players[8].denies,players[8].backpack_0,players[8].duration,players[8].assists,players[8].tower_damage,players[8].isRadiant,players[8].hero_id,players[8].win,players[8].lobby_type,players[8].ability_upgrades_arr[1],players[9].cluster,players[9].gold_spent,players[9].patch,players[9].deaths,players[9].gold_per_min,players[9].lose,players[9].kda,players[9].solo_competitive_rank,players[9].gold,players[9].radiant_win,players[9].match_id,players[9].account_id,players[9].leaver_status,players[9].hero_healing,players[9].game_mode,players[9].personaname,players[9].xp_per_min,players[9].benchmarks.gold_per_min.pct,players[9].benchmarks.gold_per_min.raw,players[9].benchmarks.tower_damage.pct,players[9].benchmarks.tower_damage.raw,players[9].benchmarks.hero_damage_per_min.pct,players[9].benchmarks.xp_per_min.pct,players[9].benchmarks.xp_per_min.raw,players[9].benchmarks.kills_per_min.pct,players[9].level,players[9].abandons,players[9].player_slot,players[9].start_time,players[9].last_hits,players[9].item_5,players[9].item_4,players[9].item_3,players[9].item_2,players[9].item_1,players[9].item_0,players[9].region,players[9].kills,players[9].hero_damage,players[9].backpack_1,players[9].backpack_2,players[9].denies,players[9].backpack_0,players[9].duration,players[9].assists,players[9].tower_damage,players[9].isRadiant,players[9].hero_id,players[9].win,players[9].lobby_type,players[9].ability_upgrades_arr[1],players[10].cluster,players[10].gold_spent,players[10].patch,players[10].deaths,players[10].gold_per_min,players[10].lose,players[10].kda,players[10].gold,players[10].radiant_win,players[10].match_id,players[10].leaver_status,players[10].hero_healing,players[10].game_mode,players[10].xp_per_min,players[10].benchmarks.gold_per_min.pct,players[10].benchmarks.gold_per_min.raw,players[10].benchmarks.tower_damage.pct,players[10].benchmarks.tower_damage.raw,players[10].benchmarks.xp_per_min.pct,players[10].benchmarks.xp_per_min.raw,players[10].level,players[10].abandons,players[10].player_slot,players[10].start_time,players[10].last_hits,players[10].item_5,players[10].item_4,players[10].item_3,players[10].item_2,players[10].item_1,players[10].item_0,players[10].region,players[10].kills,players[10].hero_damage,players[10].backpack_1,players[10].backpack_2,players[10].denies,players[10].backpack_0,players[10].duration,players[10].assists,players[10].tower_damage,players[10].isRadiant,players[10].hero_id,players[10].win,players[10].lobby_type,players[10].ability_upgrades_arr[1],match_id,lobby_type,start_time,leagueid,dire_score,game_mode,positive_votes,radiant_score,tower_status_dire,barracks_status_dire,region

public class CSVReader {


	public static void main(String[] args) throws Exception {
		read(Constants.FILES_MATCHES, 21);
	}

	public static void read(String csvFile, int i) throws Exception {

		BufferedReader br = null;
		String line = "";

		try {
			// for (int i = 0; i <= 18; i++) {
			br = new BufferedReader(new FileReader(csvFile + i + Constants.CSV));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] data = line.split(Constants.CVS_SPLIT_BY);
				System.out.println("match_id = " + data[2]);
				if (data[2].compareToIgnoreCase("result.matches[100].match_id") != 0)
					extractCSVFrom(Constants.API_OPENDOTA + "/matches/" + data[2], i);
				// }
			}
			// extractCSVFrom("https://api.opendota.com/api/matches/3393075927",9999);
			// extractCSVFrom("https://api.opendota.com/api/matches/3385992942",9999);

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
	 * @param to
	 *            int local
	 */
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
	 */
	private static void parseJSONtoCSV(List<Map<String, String>> flatJson, int to, String json) {
//		FileUtils.write(new File(Constants.FILES_BY_HERO + "jsons/00-" +to+"/04-"+to+ Constants.JSON), json, "ISO8859_1");
//		
		// flatJson = JSONFlattener.parseJson(new
		// File("files/sample_large.json"), "UTF-8");
		// Using ';' as separator
		Set<String> header = CSVWriter.collectHeaders(flatJson);
		// the intention is generate a csv file with specific headers - not all
		// if (to == 9999)
		CSVWriter.writeLargeFile(flatJson, ",", Constants.FILES_OUT + "matches/" + to + Constants.CSV, header);
		// else
		// CSVWriter.writeLargeFileWithoutHeader(flatJson, ",",
		// "files/out/matches/"+to+".csv", header);
	}

}
