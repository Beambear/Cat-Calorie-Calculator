package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import util.InputErrorException;

public class User extends Account{
	private ArrayList<Cat> catList = new ArrayList<Cat>();
	private ArrayList<String> dataToSave = new ArrayList<String>();
	String[] userData;
	public User(String username) {
		String filePath = ("user_saved\\"+username+"_Data.txt");	
		Path myPath = Paths.get(filePath);
		try {
			String dataRead = Files.readString(myPath);	//read data from file path
			userData = dataRead.split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUsername(userData[0]);
		setPassword(userData[1]);
	}
	
	public void userMenu() {
		System.out.println("login completed"+"\n\nHi "+getUsername()+", Welcome Back! ");
		//loadCats();
		String input ="";
		do {
		loadCats();
		System.out.println("\n"+"What do you want to do:");
		System.out.println("(1)Check cats status\n(2)Edit cat's status \n(3)Add new cat \n(4)Remove cat	\n(5)Save and log out");
		Scanner keyboard = new Scanner(System.in);
		input = keyboard.nextLine();
		switch(input) {
		case "1" : checkStatue(); break;
		case "2" : editStatue(); saveAllData();break;
		case "3" : addNewCat(); saveAllData();break;
		case "4" : deleteCat(); saveAllData();break;
		case "5" : saveAllData(); return;
		default: System.out.println("Error: invaild input, try again.");
		}
		}while(!input.equals("5"));
	}
	
	private void deleteCat() {
		System.out.println("Which cat do you want to remove from list? (0)=back");
		int catNumber = 0;
		for(int i=0; i<catList.size();i++) {					//print each cats' name from catList
			System.out.print(catList.get(i).getCatName()+"; ");
		}
		//get cat name input
		boolean check = false;
		do {
			String catPicked = askStringInput();
			if(catPicked.equals("0"))
			{
				return;
			}
			for(int i=0;i<catList.size();i++)
			{
				if(catPicked.equals(catList.get(i).getCatName()))	//find cat in catList
				{
					catNumber = i;	//get cat number in catList
					check = true;
				}
			}
			if(check==false)
			{
				System.out.println(catPicked+" is not in you cat List.");
			}
		}while(check==false);
		System.out.println("Sure to remove "+catList.get(catNumber).getCatName()+"? (Y/N)");
		String sureCheck ="";
		do {
			sureCheck = askStringInput();
			switch(sureCheck)
			{
			case "y":
				String removedName= catList.get(catNumber).getCatName();
				catList.remove(catNumber);
				System.out.println(removedName +" has been removed from list.");
			//	saveAllData();
				System.out.println("");
				System.out.println("");
				return;
			case "n":
				sureCheck = "n";
				System.out.println("");
				System.out.println("");
				break;
			default:
					System.out.println("Error: invaild input, try again (Y/N)");
					break;
			}
		}while(!sureCheck.equals("n"));
	}
	
	private void editStatue() {
		System.out.println("Which cat do you want to edit?");
		int catNumber = 0;
		for(int i=0; i<catList.size();i++) {					//print each cats' name from catList
			System.out.print(catList.get(i).getCatName()+"; ");
		}
		//get cat name input
		boolean check = false;
		do {
			String catPicked = askStringInput();
			for(int i=0;i<catList.size();i++)
			{
				if(catPicked.equals(catList.get(i).getCatName()))	//find cat in catllist
				{
					catNumber = i;	//get cat number in cat list
					check = true;
				}
			}
			if(check==false)
			{
				System.out.println(catPicked+" is not in you cat List.");
			}
		}while(check==false);
		//print cat info
		String checkPrint = "";
		checkPrint += "\n"+"(1)Cat Name: "+catList.get(catNumber).getCatName();
		checkPrint += "\n"+"(2)Breeder: "+ catList.get(catNumber).getBreeder();
		checkPrint += "\n"+"(3)Sex: "+catList.get(catNumber).getSex();
		checkPrint += "\n"+"(4)Age: "+ catList.get(catNumber).getAge();
		checkPrint += "\n"+"(5)Weight: "+catList.get(catNumber).getCatWeight();
		checkPrint += "\n"+"(6)Body Shape: "+catList.get(catNumber).getBodyShape();
		checkPrint += "\n"+"(7)Body Condition: "+catList.get(catNumber).getActiveLevel();
		checkPrint += "\n"+"(8)FoodName: "+catList.get(catNumber).getCurrFoodName();
		checkPrint += "\n"+"(9)FoodCalories: "+catList.get(catNumber).getCurrFoodCalories();
		checkPrint += "\n"+"(0)Back";
		System.out.println(checkPrint);
		System.out.println("Which one want to edit? [ 1=Cat Name; 2=Breeder;...etc]");
		boolean optionCheck = false;
		String option ="";
		do {
			option = askStringInput();
			optionCheck = option.matches("[0-9]");
			if(optionCheck == false)
			{
				System.out.println("Input is invalid, [1~9] only.");
			}
		}while(optionCheck == false);
		System.out.print("new value: ");
		switch(option)
		{
		case "0" :break;
		case "1" :
			String newName = askStringInput();
			catList.get(catNumber).setCatName(newName);
			break;
		case "2" :
			String newBreeder = askStringInput();
			catList.get(catNumber).setBreeder(newBreeder);
			break;
		case "3" :
			String newSex = askStringInput();
			catList.get(catNumber).setBreeder(newSex);
			break;
		case "4" :
			try {
				double newAge =askPositiveInput();
				catList.get(catNumber).setAge(newAge);
			} catch (InputErrorException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			break;
		case "5" :
			try {
				double newWeight = askPositiveInput();
				catList.get(catNumber).setCatWeight(newWeight);
			} catch (InputErrorException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}			
			break;
		case "6" :
			System.out.println("Cat body shape: [overweight, underweight, ideal] ");
			String newShape ="";
			boolean shapeCheck=false;
			do {
				newShape = askStringInput();
				newShape = newShape.toLowerCase();
				if(newShape.equals("overweight")||newShape.equals("underweight")||newShape.equals("ideal")) {
				catList.get(catNumber).setBodyShape(newShape);
				shapeCheck = true;
				}
				System.out.println("invalid input. enter [overweight, underweight, ideal]");
			}while(shapeCheck == false);
			break;
		case "7" :
			
			System.out.println("Cat body condition: [spayed, neutered, intact] ");
			String newCondition = "";
			boolean conditionCheck = false;
			do {
				newCondition = askStringInput();
				newCondition = newCondition.toLowerCase();
				if(newCondition.equals("spayed")||newCondition.equals("neutered")||newCondition.equals("intact")) {
					catList.get(catNumber).setActiveLevel(newCondition);
					conditionCheck = true;
					break;
				}
				System.out.println("invalid input. enter [spayed, neutered, intact]");
			}while(conditionCheck == true);			
			break;
		case "8" :
			String newFoodName = askStringInput();
			catList.get(catNumber).setCurrFoodName(newFoodName);
			break;
		case "9" :
			double newFoodCalD;
			try {
				newFoodCalD = askPositiveInput();
				int newFoodCal =(int) newFoodCalD;
				catList.get(catNumber).setCurrFoodCalories(newFoodCal);
			} catch (InputErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		System.out.println("Saving changes...");
	}
	
	private void loadCats() {
		catList.clear();						//clear all before load
		String filePath = ("user_saved\\"+getUsername()+"_Data.txt");	
		Path myPath = Paths.get(filePath);
		try {
			String dataRead = Files.readString(myPath);	//read data from file path
			userData = dataRead.split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] catInfo = new String[(userData.length-2)];
		for(int i=2; i<userData.length;i++)		//first two is username and password
		{
			catInfo[i-2]=userData[i];			//don't read username and password as cat Info
		}

		int dataLength = catInfo.length;
		String name ="";
		String breeder ="";
		String sex ="";
		String age ="";
		String weight ="";
		String shape ="";
		String activeLevel ="";
		String foodName ="";
		String foodCal ="";
		for(int i=0; i<(dataLength/9);i++)
		{	//get attributes
			name = catInfo[i*9];
			breeder = catInfo[i*9+1];
			sex = catInfo[i*9+2];
			age = catInfo[i*9+3];
			double ageD = Double.valueOf(age);			//change string to double
			weight = catInfo[i*9+4];
			double weightD = Double.valueOf(weight);	//change string to double
			shape = catInfo[i*9+5];
			activeLevel = catInfo[i*9+6];
			foodName = catInfo[i*9+7];
			foodCal = catInfo[i*9+8];
			double foodCalD = Double.valueOf(foodCal);	//change string to double
			Cat myCat = new Cat(name,breeder,sex,ageD,weightD,shape,activeLevel,foodName,foodCalD);
			catList.add(myCat);
			//System.out.println("show catlist length: "+catList.size());
		}
	}
	
	private void checkStatue() {
		String checkPrint ="";
		for(int i=0; i<catList.size();i++)
		{
			checkPrint += catList.get(i).getCatName()+": ";
			checkPrint += " ["+ catList.get(i).getBreeder()+"]";
			checkPrint += "\n"+"Age: "+ catList.get(i).getAge()+"year(s) old";
			checkPrint += "  Weight: "+catList.get(i).getCatWeight()+"kg";
			checkPrint += "\n"+"Body Condition: "+catList.get(i).getBodyShape()+" "+catList.get(i).getActiveLevel();
			checkPrint += "\n"+"\n"+"Basal Metabolic Rate(BMR): "+catList.get(i).getBMR()+" Kcal/day"+"\n";
			checkPrint += "Target metabolic rate: "+ catList.get(i).getTMR()+" Kcal/day"+"\n";
			checkPrint += "Recommended daily intake: "+catList.get(i).getCurrFoodName()+"_"+ catList.get(i).getIntakePerDay()+"gram";
			checkPrint += "\n"+"\n";
			}
		System.out.println(checkPrint);
		System.out.println("Back to menu?(Y)");
		String backToMenu ="";
		do {
			backToMenu = askStringInput();
			if(backToMenu.equals("y"))
			{
				break;
			}
		}while(true);
	}
	
	private void addNewCat() {//String catName,String breeder,String sex,double age,double catWeight,
//		String bodyShape,String activeLevel,String currFoodName,int currFoodCalories
		
			System.out.print("Cat Name: ");
			String catName = askStringInput();
			System.out.print("Cat breeder: ");
			String catBreeder = askStringInput();
			System.out.print("Cat sex: ");
			String catSex = askStringInput();
			System.out.println("Cat age: (if not 1 year old, 8 month = 0.08. 11 month = 0.11) ");
			double catAge = askDoubleInput();
			System.out.println("Cat weight: [Unit:kg] ");
			double catWeight= askDoubleInput();
			System.out.println("Cat body shape: [overweight, underweight, ideal] ");
			String catBodyShape = "";
			do {
				catBodyShape = askStringInput();
				catBodyShape = catBodyShape.toLowerCase();
				if(catBodyShape.equals("overweight")||catBodyShape.equals("underweight")||catBodyShape.equals("ideal")) {
				break;
				}
				System.out.println("invalid input. enter [overweight, underweight, ideal]");
			}while(true);
			System.out.println("Cat body condition: [spayed, neutered, intact] ");
			String catBodyCondition = "";
			do {
				catBodyCondition = askStringInput();
				catBodyCondition = catBodyCondition.toLowerCase();
				if(catBodyCondition.equals("spayed")||catBodyCondition.equals("neutered")||catBodyCondition.equals("intact")) {
					break;
				}
				System.out.println("invalid input. enter [spayed, neutered, intact]");
			}while(true);
			System.out.print("Cat food name: ");
			String catFoodName = askStringInput();
			System.out.print("Cat food unit Calories: [Kcal/kg] ");
			double catFoodCalories = askDoubleInput();
			Cat newCat = new Cat(catName,catBreeder,catSex,catAge,catWeight,catBodyShape,catBodyCondition,catFoodName,catFoodCalories);
			catList.add(newCat); //add Cat into cat list
	}
	
	private void saveAllData() {
		dataToSave.clear();
		dataToSave.add(getUsername()+"");	//add user name
		dataToSave.add(getPassword()+"");	//add password
		//Then add cat info
		System.out.println("Saving...50%");
		int addLength = catList.size();		//get number of cats
		for(int i=0; i<addLength;i++)
		{
			dataToSave.add(catList.get(i).getCatName());		//save cat name
			dataToSave.add(catList.get(i).getBreeder());		//save breeder
			dataToSave.add(catList.get(i).getSex());			//save sex
			double catAgeD = Math.round(catList.get(i).getAge()*100);
			catAgeD = catAgeD/100;
			String catAgeS = Double.toString(catAgeD);
			dataToSave.add(catAgeS);							//save age
			double catWeightD = Math.round(catList.get(i).getCatWeight()*100);
			catWeightD = catWeightD/100;
			String catWeightS = Double.toString(catWeightD);
			dataToSave.add(catWeightS);							//save weight
			dataToSave.add(catList.get(i).getBodyShape());		//save body shape
			dataToSave.add(catList.get(i).getActiveLevel());	//save body condition (active Level)
			dataToSave.add(catList.get(i).getCurrFoodName());	//save food name
			double catFoodCalD = Math.round(catList.get(i).getCurrFoodCalories()*100);
			catFoodCalD= catFoodCalD/100;
			String catFoodCalS = Double.toString(catFoodCalD);
			dataToSave.add(catFoodCalS);						//save food calories
		}
		//Then save All data to local text
		System.out.println("Saving...80%");
		String filePath=("user_saved\\"+getUsername()+"_Data.txt");		//set file path
		File userFile = new File(filePath);								//set file
		try {
			FileOutputStream fop = new FileOutputStream(userFile);		//initialize fop
			OutputStreamWriter writer = new OutputStreamWriter(fop);	//initialize writer
			int writeLength = dataToSave.size();
			for(int i=0;i<writeLength;i++)
			{
				String currData = dataToSave.get(i)+" ";
//				System.out.println(currData);
				writer.append(currData);
				
			}
			writer.close();
			fop.close();
			System.out.println("Completed");
		}catch(IOException e) {
			//e.printStackTrace();
		}
	}
	
	private double askDoubleInput() {
		double inputDouble =-1;
		while(inputDouble<0) {
			try {
				inputDouble = askPositiveInput();
			} catch (InputErrorException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return inputDouble;
	}
	
	private double askPositiveInput() throws InputErrorException{
		double inputDouble =0;							//initialize double
		Scanner keyboard = new Scanner(System.in);
		if(keyboard.hasNextDouble())					//if input is number
		{
			inputDouble = keyboard.nextDouble();
			if(inputDouble >=0)							//if input number is positive
			{
				return inputDouble;						//return 
			}else {										//else
				throw new InputErrorException("0");		//go case 0
			}
		}else {
			throw new InputErrorException("N");			//go case not number
		}
	}
	
	private String askStringInput() {
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
