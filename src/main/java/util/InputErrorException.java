package util;

import java.io.IOException;

public class InputErrorException extends IOException {
	public InputErrorException(String errorCode) {
		System.out.println("Your input is invalid,please try again.");
		switch(errorCode) {
		case "N":
			notNumber();
			break;
		case "0":
			lessThanZero();
			break;
		case"NI":
			notIntNumber();
		}
	}
	public void notNumber() {		//error Code: N
		System.out.println("Enter number only");
	}
	public void lessThanZero() {	//error Code: 0
		System.out.println("input cannot be negative.");
	}
	public void notIntNumber() {
		System.out.println("Enter Integer number only");
	}
}
