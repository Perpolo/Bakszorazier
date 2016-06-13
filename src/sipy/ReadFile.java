package sipy;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;

public class ReadFile
{

	private static final String filePath = "pytaniaSip.txt";
	String test;

	ReadFile()
	{

		try
		{
			test = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		List<Pytanie> listaPytan = new ArrayList<Pytanie>();

		String lines[] = test.split("\\r?\\n");

		boolean etap = false;

		Pytanie nowePytanie = new Pytanie();

		for (int i = 0; i < lines.length; i++)
		{

			String linia = lines[i];

			if (linia.isEmpty())
			{} else if (etap)
			{
				if (Character.isDigit(linia.charAt(0)))
				{
					nowePytanie.setPytanie(linia);
					etap = false;
				} else
					System.err.println("Klopsik z pytaniem - 1: " + i);
			} else
			{

				if (linia.startsWith("a"))
				{
					nowePytanie.setA(linia);
				} else if (linia.startsWith("b"))
				{
					nowePytanie.setB(linia);
				} else if (linia.startsWith("c"))
				{
					nowePytanie.setC(linia);
				} else if (linia.startsWith("("))
				{
					if (linia.startsWith("(a"))
					{
						nowePytanie.setA(linia);
					} else if (linia.startsWith("(b"))
					{
						nowePytanie.setB(linia);
					} else if (linia.startsWith("(c"))
					{
						nowePytanie.setC(linia);
					} else
						System.err.println("Klopsik z pytaniem - 2");
				} else
				{
					i--;
					etap = true;
					listaPytan.add(nowePytanie);
					nowePytanie = new Pytanie();
				}
			}
		}
		ListaPytan lp = new ListaPytan();
		lp.lp = listaPytan;
		Gson gson = new Gson();
		String json = gson.toJson(lp);

//		try
//		{
//
//			FileWriter file = new FileWriter("pytaniaSip.json");
//			file.write(json);
//			file.flush();
//			file.close();
//
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}

		System.out.println(json);
	}
}