package com.demo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LogFileReader {

	public void readFile(String path, String expectedString, int numberOfLine) throws IOException {

		File f = new File(path);
		Scanner sc = new Scanner(f);
		int count=0;

		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if (line.contains(expectedString)){
				count++;
				System.out.println("Expected Error Line--->  "+ line);
				int j=1;
				System.out.println("Printing next "+ numberOfLine + " Lines after found the expected Error " + expectedString);
				while(j<=numberOfLine && sc.hasNextLine()){
					line = sc.nextLine();
					System.out.println(j +" Line--> "+line);
					j++;
					if(j>numberOfLine) {
						break;
					}
				}
				System.out.println("******************************************************");

			} 

		}   
		System.out.println("WARNING... "+expectedString + " message found: "+ count + " times in the log file!");

		sc.close();

	}

}
