package Gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.Graph;

public class MyParser {
	public MyParser(Graph graph) {
		
	}
	
//	loadGraphFromFile methode
//	Filename �bergeben alsd parameter
	private String readGraphFromFile(File file) {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String fileLines = "";
			while(reader.readLine() != null) {
				fileLines = fileLines + reader.readLine() + "\n";
			}
			
			return fileLines;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
//	renderedge methode
//	bekommt eine dateizeile -> Kanten
//	http://openbook.galileocomputing.de/javainsel/javainsel_15_004.html#dodtpac233475-9da3-4cec-9eab-d68a36830773 file reader
//	
	
	
}
