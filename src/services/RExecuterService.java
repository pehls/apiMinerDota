package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import utils.Constants;

public class RExecuterService {
	private Runtime runtime;

	public RExecuterService() {
		super();
		// TODO Auto-generated constructor stub
	}

	// C:\Program Files\R\R-3.4.0\bin\x64\R.exe
	public boolean Run(int hero_id) throws IOException {
		this.runtime = Runtime.getRuntime();
		openR(runtime);
		loadFunctionsToR(runtime);
		return FileExists(Constants.FILES_BY_HERO + "\\00-" + hero_id + "\\" + hero_id + Constants.JSON);

	}

	private void openR(Runtime runtime) throws IOException {
		this.runtime.exec("C:\\Program Files\\R\\R-3.4.0\\bin\\x64\\R.exe");
	}

	private boolean FileExists(String url) {
		File file = new File(url);
		if (file.exists())
			return true;
		return false;
	}

	private void loadFunctionsToR(Runtime runtime) throws IOException {
		this.runtime
				.exec("source(\"C:\\Users\\gabri\\OneDrive\\Documentos\\GitHub\\apiMinerDota\\src\\utils\\getByIDSFromURI.R\")");
		this.runtime.exec(
				"source(\"C:\\Users\\gabri\\OneDrive\\Documentos\\GitHub\\apiMinerDota\\src\\utils\\getMatchIDSFromJSON.R\")");
		this.runtime.exec("source(\"C:\\Users\\gabri\\OneDrive\\Documentos\\GitHub\\apiMinerDota\\src\\utils\\getTree.R\")");
		this.runtime.exec("source(\"C:\\Users\\gabri\\OneDrive\\Documentos\\GitHub\\apiMinerDota\\src\\utils\\getTreeCV.R\")");
		this.runtime.exec("source(\"C:\\Users\\gabri\\OneDrive\\Documentos\\GitHub\\apiMinerDota\\src\\utils\\getVariables.R\")");
		this.runtime.exec("source(\"C:\\Users\\gabri\\OneDrive\\Documentos\\GitHub\\apiMinerDota\\src\\utils\\normalize.R\")");

	}

	public void runCSVLoad(int nFiles, int hero_id) throws IOException {
		this.runtime.exec("getByIDSFromURI(getMatchIDSfromJSON(" + nFiles + "," + hero_id + ")," + hero_id + ")");
	}

	
	public int getNumberOfFiles() {
		return readFile("../apiMinerDota/src/utils/properties");
	}

	public int readFile(String file) {
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

	public boolean getTrees(int hero_id) throws IOException {
		this.runtime.exec("getTreeCV(" + hero_id + ")");
		this.runtime.exec("getTree(" + hero_id + ")");
		return true;
	}

	public boolean normalize(int hero_id) throws IOException {
		this.runtime.exec("normalize(" + hero_id + ")");
		return true;

	}

}
