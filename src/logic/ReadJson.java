package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class ReadJson
{
	private static final String filePath = "pytaniaSip.json";
	public ReadJson()
	{

	}
	public static ListaPytan getData()
	{
		String jsonString = null;
		try
		{
			jsonString = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		Gson gson = new Gson();
		ListaPytan lp = gson.fromJson(jsonString, ListaPytan.class);


		return lp;
	}
}
