package com.demo;

import java.io.File;

public class TestClass {

	public static void main(String[] args) throws Throwable {
		

		// Location A
		String path1 = System.getProperty("user.dir")+"\\Resource\\csv1.csv";

		// Location B
		String path2 = System.getProperty("user.dir")+"\\Resource2\\csv1.csv";

		String watchDir = System.getProperty("user.dir")+"\\Resource2\\";

		File source = new File(path1);
		File dest = new File(path2);
		
		
		//Scenario 1: User is alerted when a pipe delimited file is dropped in to a folder
		
		CSVClass csv = new CSVClass();
		
		// Delete Old CSV File
		csv.deleteExistingFile(path1);
		csv.deleteExistingFile(path2);
		
		// Enter data 
		// Path of CSV file and how many sets of Data user wants to enter?
		csv.enterData(path1, 25);
		
		// Watcher Service when file copied from Location A to B
		csv.watcher(watchDir, source, dest);
		
		System.out.println("10 Seconds waiting before running the next scenario");
		Thread.sleep(10000);
		System.out.println();
		System.out.println();
		System.out.println();
		
		//****************************************************************************************************
	
		//Scenario 2: Parse a log file for a given string and count number of its occurrences
		
		String filePath1 = System.getProperty("user.dir")+"\\Resource\\server.log";
		String filePath2 = System.getProperty("user.dir")+"\\Resource\\server2.log";
		
		String findString = "500 Server error";
		
		LogFileReader read = new LogFileReader();
		
		read.readFile(filePath1, findString, 5);
		System.out.println();
		System.out.println();
		System.out.println();
		
		read.readFile(filePath2, findString, 6);

	}

}
