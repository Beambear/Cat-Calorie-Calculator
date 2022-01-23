package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Processor {
	private String username;
	private String password;
	private User dearUser;
	private Administrator damnAdmin;
	private ArrayList<String> adminFileData = new ArrayList<String>();

	
	public Processor() {
		String dirname ="user_saved";
		File setup = new File(dirname);
		setup.mkdirs();
		username = "admin";
		if(accountExists()==false	) {
			password ="admin";
			createNewFile();
		}
		getAdminFileData();
	}
	
	public boolean login() {
		//ask username//
		System.out.print("Login:"+"\n"+"Username:");
		username = askInput();
		if(accountExists()==false){
			{
				System.out.println("Error: this username has not been signed up.");
				return false;
			}
		}
		//read text file, get password and other attributes//
		
		//ask password//
		System.out.print("Password:");
		password = askInput();
		loginCheck();
//		System.out.println("Login...");
//		System.out.println("Login completed");
		return true;
	}
	
	private boolean accountExists() {
		String filePath=("user_saved\\"+username+"_Data.txt");
		File userFile = new File(filePath);
		if(userFile.exists())
		{
			//System.out.println("exists");
			return true;
		}
		//System.out.println("doesn't exists");
		return false;
	}
	
	private void loginCheck() {
		switch(username) {
		case "admin":
			damnAdmin = new Administrator();					//admin login
			if(password.equals(damnAdmin.getPassword())) {
				damnAdmin.adminMenu();
			}else {
				System.out.println("adminstrator login failed");
			}
			break;
			
			default:
			dearUser = new User(username);								//user login
			if(password.equals(dearUser.getPassword())) {
				dearUser.userMenu();
			}else {
				System.out.println("username and password not match, please try again.");
			}
		}
	}
	
	public void signUp() {
		System.out.print("Sign Up:"+"\n"+"Username:");
		username = askInput();
		if(accountExists() == true)
		{
			System.out.print("'"+username+"' has been used.");
			while(accountExists()==true) 
			{
				Random rand = new Random();
				int randomNum = rand.nextInt(666);
				username= username+randomNum;
			}
			System.out.println("\n"+"You can try: "+username+"\n");
			return;
		}
		System.out.print("Set password:");
		password = askInput();
		createNewFile();
		System.out.println("Completed.");
		System.out.println("login...");
		loginCheck();
	}
	
	
	private void createNewFile() {
		System.out.println("Creating...");
		String filePath=("user_saved\\"+username+"_Data.txt");			//set file path
		File userFile = new File(filePath);								//set file
		try {
			userFile.createNewFile();									//create new file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		try {
			FileOutputStream fop = new FileOutputStream(userFile);		//initialize fop
			OutputStreamWriter writer = new OutputStreamWriter(fop);	//initialize writer
			writer.append(username+" ");	//save username
			writer.append(password+" ");	//save password
			writer.close();
			fop.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		adminFileData.add(username);
		adminFileData.add(password);
		saveAdminFileData();
	}
	
	private void getAdminFileData() {
		String filePath = ("user_saved\\admin_Data.txt");	
		Path myPath = Paths.get(filePath);
		String dataRead = "";
		try {
			dataRead = Files.readString(myPath);	//read data from file path
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] adminDataArray = dataRead.split(" ");

		for(int i=0;i<adminDataArray.length;i++) {
			String currData= adminDataArray[i];
			adminFileData.add(currData);
		}
	}
	
	private void saveAdminFileData() {
		System.out.println("Saving...");
		String filePath=("user_saved\\admin_Data.txt");			//set file path
		File userFile = new File(filePath);								//set file
		try {
			FileOutputStream fop = new FileOutputStream(userFile);		//initialize fop
			OutputStreamWriter writer = new OutputStreamWriter(fop);	//initialize writer
			int writeLength = adminFileData.size();
			for(int i=0;i<writeLength;i++)
			{
				String currData = adminFileData.get(i)+" ";
				writer.append(currData);
				
			}
			writer.close();
			fop.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private String askInput() {
		Scanner keyboard = new Scanner(System.in);
		boolean inputCheck = false;
		String input = "";
		do {
			input = keyboard.nextLine();
			input = input.toLowerCase();
			inputCheck = input.matches("[A-Za-z0-9]+");	//check input no number or symbol	
			if(inputCheck == false) {
				System.out.println("Input is invalid, There is symbol or blank space.");
			}
		}while(inputCheck == false);
		return input;
	}
}
