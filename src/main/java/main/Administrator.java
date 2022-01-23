package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

// two command : print and back
public class Administrator extends Account{
	private String[] adminData;
	public Administrator() {
		String filePath = ("user_saved\\admin_Data.txt");	
		Path myPath = Paths.get(filePath);
		try {
			String dataRead = Files.readString(myPath);	//read data from file path
			adminData = dataRead.split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setPassword(adminData[1]);
	}
	
	public void adminMenu() {
		System.out.println("adminstrator login completed");
		Scanner keyboard = new Scanner(System.in);
		String input ="";
		do{
			input = keyboard.nextLine();
		switch(input) {
		case "print" : 
			printUserInfo();
			break;
		case "back" :
			break;
		default:
			break;
			}
		}while(!input.equals("back"));
	}
	
	private void printUserInfo() {
		int i=0;
		while(i<(adminData.length))
		{
			String userInfo = "username: "+adminData[i];
			userInfo +="	password: "+adminData[i+1];
			System.out.println(userInfo);
			i++;
			i++;
		}
	}
}
