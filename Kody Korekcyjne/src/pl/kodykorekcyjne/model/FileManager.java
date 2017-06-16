package pl.kodykorekcyjne.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileManager
{
	private static String fileName;

	public static void writeRaportToFile(File file, StringBuffer output)
	{
		fileName = file.getAbsolutePath();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)))
		{
			bw.write(output.toString());

			System.out.println("Zapisano dane w pliku: " + fileName);
		} catch (FileNotFoundException e)
		{
			System.err.println("Nie odnaleziono pliku " + fileName);
		} catch (IOException e)
		{
			System.err.println("B³¹d podczas zapisu danych do pliku " + fileName);
		}
	}

	public static String readInputFromFile(File file) throws FileNotFoundException, IOException
	{
		fileName = file.getAbsolutePath();
		StringBuffer input = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath())))
		{
			String str;
			while ((str = br.readLine()) != null)
			{
				input.append(str);
			}
			System.out.println("Wczytano dane z pliku: " + fileName);

		} catch (FileNotFoundException e)
		{
			System.err.println("Nie odnaleziono pliku " + fileName);
			throw e;
		} catch (IOException e)
		{
			System.err.println("B³¹d podczas odczytu danych z pliku " + fileName);
			throw e;
		}
		return input.toString();
	}

}
