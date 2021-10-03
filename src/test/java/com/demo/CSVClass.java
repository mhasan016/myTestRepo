package com.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class CSVClass {

	public void enterData(String path, int numberOfRow) throws Exception {


		FileWriter w = new FileWriter(path, true);

		w.append("ID"); w.append("|"); w.append("LastName"); w.append("|");
		w.append("FirstName"); w.append("|"); w.append("Gender"); w.append("|");
		w.append("DOB"); w.append("|"); w.append("Address"); w.append("\n");

		CSVClass a = new CSVClass();

		for(int i=1;i<=numberOfRow;i++) {
			
			w.append(a.generateId(1000,2000)); w.append("|"); w.append(a.generateRandomString("lastName", 4)); w.append("|");
			w.append(a.generateRandomString("firstName", 5)); w.append("|"); w.append(a.selectGender()); w.append("|");
			w.append(a.birthDate(1950, 2010)); w.append("|"); w.append(a.generateRandomString("address", 10)); w.append("\n");
		}

		w.flush();
		w.close();
		System.out.println("CSV File created");
	}


	public void deleteExistingFile(String fileName) { 

		File f = new File(fileName); 
		boolean exists = f.exists();
		if(exists) {
			f.delete();
			System.out.println("Deleted the file: " + f.getName());
		}else {
			System.out.println(f.getName() + " not exist in the directory");
		} 
	} 

	public String generateId(int low, int high) {

		Random r = new Random();
		int idd = r.nextInt(high-low) + low;
		//System.out.println(id);
		String id = String.valueOf(idd);
		return id;		
	}

	public String selectGender() {

		ArrayList<String> gender = new ArrayList<String>();
		gender.add("M");
		gender.add("F");
		Collections.shuffle(gender);

		String g = gender.get(0);
		//System.out.println("\nShuffled List : \n" + g);
		return g;

	}


	public String birthDate(int yy1, int yy2) throws Exception {

		LocalDate startDate = LocalDate.of(yy1, 1, 1);
		long start = startDate.toEpochDay();

		LocalDate endDate = LocalDate.of(yy2, 12, 12);
		long end = endDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();

		LocalDate expDate= LocalDate.ofEpochDay(randomEpochDay);
		return expDate.toString();
	}

	public String generateRandomString(String str, int stringSize) {
		String rndmString= "";
		if(str.equalsIgnoreCase("LastName") || str.equalsIgnoreCase("lName")){
			// Generate Last Name
			rndmString = RandomStringUtils.randomAlphabetic(stringSize);

		}else if (str.equalsIgnoreCase("FirstName") || str.equalsIgnoreCase("fName")){
			// Generate First Name
			rndmString = RandomStringUtils.randomAlphabetic(stringSize);
		}else {
			// Generate Address
			rndmString = RandomStringUtils.randomAlphabetic(stringSize);
		}
		//System.out.println("Random " + str +" " +rndmString);
		return rndmString;

	}


	public void watcher(String Directory, File src, File dest) throws Throwable {

		WatchService Service = FileSystems.getDefault().newWatchService();
		Path dir = Paths.get(Directory);
		WatchKey key = dir.register(Service, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.ENTRY_DELETE);

		WatchFileMoving(dir,src,dest);

		while(true)
		{
			//System.out.println("Start polling");
			key = Service.poll(2, TimeUnit.SECONDS);
			//System.out.println("Finished polling and retrieved key");

			if(key != null)
			{
				for (@SuppressWarnings("unused") WatchEvent<?> event : key.pollEvents())
				{
					//System.out.println(" Event Type "+ event.kind()+ " . Detail "+ event.context());
					System.out.println("Alert !!! New file added to watch directory---> " + Directory);
				}

				if (!key.reset())
				{
					System.out.println("Key Reset");
					break;
				}   
			}else {
				System.out.println("Breaking out of loop as watch key null ");
				break;
			}
		}
	}

	public void moveFiles(File src, File dest) {
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void WatchFileMoving(Path paths, File src, File dest)
	{
		CSVClass a = new CSVClass();
		a.moveFiles(src, dest);

	}
}
