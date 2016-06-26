package logic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ReadFile {

	private static final String filePath = "tsst.txt";
	String test;

	public ReadFile() {

		try {
			test = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Question> listaPytan = new ArrayList<Question>();

		String lines[] = test.split("\\r?\\n");

		boolean etap = false;

		Question nowePytanie = new Question();

		for (int i = 0; i < lines.length - 6; i++) {

			String linia = lines[i];

			if (Character.isDigit(lines[i].charAt(0))) {
				if (lines[i + 1].startsWith("a)"))
					if (lines[i + 2].startsWith("b)"))
						if (lines[i + 3].startsWith("c)"))
							if (lines[i + 4].startsWith("d)")) {
								nowePytanie.setPytanie(linia.replace("  ","").replace("#kochamPlacki123#", ""));
								nowePytanie.setA(lines[i + 1].replace("  ","").replace("#kochamPlacki123#", ""));
								nowePytanie.setB(lines[i + 2].replace("  ","").replace("#kochamPlacki123#", ""));
								nowePytanie.setC(lines[i + 3].replace("  ","").replace("#kochamPlacki123#", ""));
								nowePytanie.setD(lines[i + 4].replace("  ","").replace("#kochamPlacki123#", ""));

								if (lines[i + 1].contains("#kochamPlacki123#"))
									nowePytanie.setPoprawne("a");
								else if (lines[i + 2].contains("#kochamPlacki123#"))
									nowePytanie.setPoprawne("b");
								else if (lines[i + 3].contains("#kochamPlacki123#"))
									nowePytanie.setPoprawne("c");
								else if (lines[i + 4].contains("#kochamPlacki123#"))
									nowePytanie.setPoprawne("d");
								else
									System.err.println("BYM : " + i);

								listaPytan.add(nowePytanie);
								nowePytanie = new Question();
							}
			}
			//
			// if (linia.isEmpty()) {
			// } else if (etap) {
			// if (Character.isDigit(linia.charAt(0))) {
			// nowePytanie.setPytanie(linia);
			// etap = false;
			// } else
			// System.err.println("Klopsik z pytaniem - 1: " + i);
			// } else {
			//
			// if (linia.startsWith("a")) {
			// nowePytanie.setA(linia);
			// } else if (linia.startsWith("b")) {
			// nowePytanie.setB(linia);
			// } else if (linia.startsWith("c")) {
			// nowePytanie.setC(linia);
			// } else if (linia.startsWith("(")) {
			// if (linia.startsWith("(a")) {
			// nowePytanie.setA(linia);
			// } else if (linia.startsWith("(b")) {
			// nowePytanie.setB(linia);
			// } else if (linia.startsWith("(c")) {
			// nowePytanie.setC(linia);
			// } else
			// System.err.println("Klopsik z pytaniem - 2");
			// } else {
			// i--;
			// etap = true;
			// listaPytan.add(nowePytanie);
			// nowePytanie = new Question();
			// }
			// }
			// }

			// try
			// {
			//
			// FileWriter file = new FileWriter("pytaniaSip.json");
			// file.write(json);
			// file.flush();
			// file.close();
			//
			// } catch (IOException e)
			// {
			// e.printStackTrace();
			// }
		}
		ListaPytan lp = new ListaPytan();
		lp.lp = listaPytan;
		Gson gson = new Gson();
		String json = gson.toJson(lp);

		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream("tsst.json"), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
			    out.write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			    try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		System.out.println(json);
		
	}
}