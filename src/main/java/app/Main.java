package app;

import main.Processor;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main doIt = new Main();
		doIt.catCalorieCalculator();
	}
		
	public void catCalorieCalculator() {
		Processor HomePage = new Processor();
		System.out.println("Welcome to cat calorie calculator.");
		while(!false)
		{
			System.out.println("What you want to do:");
			System.out.println("(1)Login	(2)Sign up	(3)Exit");
			Scanner keyboard = new Scanner(System.in);
			String option = keyboard.nextLine();
			switch(option) 
			{
			case "1":
				HomePage.login();
				break;
			case "2":
				HomePage.signUp();
				break;
			case "3":
				System.out.println("Exited, see you next time.");
				System.exit(0);
			default:
				System.out.println("invalid input, please try again.");
				break;
			}
		}
	}
}
