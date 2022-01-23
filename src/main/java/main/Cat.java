package main;

public class Cat {
	private String catName;
	private String breeder;
	private String sex;
	private double age;	// if not 1 year old yet, example -> 0.09 = 0 year 9 month
	private double catWeight;	// unit kg
	private String bodyShape;	//	[overweight, underweight, Ideal]
	private String activeLevel;	//	[spayed, neutered, Intact]
	private String currFoodName;
	private double currFoodCalories;	//Kcal/kg
	//CALCULATED ATTRIBUTES
	private int BMR;//(basal metabolic rate)  Kcal/day
	private int TMR;//(target metabolic rate) Kcal/day
	private int intakePerDay;	//g


	public Cat(String catName,String breeder,String sex,double age,double catWeight,
			String bodyShape,String activeLevel,String currFoodName,double currFoodCalories) 
	{
		this.catName = catName;
		this.breeder = breeder;
		this.sex =sex;
		this.age =age;
		this.catWeight = catWeight;
		this.bodyShape = bodyShape;
		this.activeLevel=activeLevel;
		this.currFoodName = currFoodName;
		this.currFoodCalories=currFoodCalories;
		calculateBMR();
		calculateintakePerDay();
	}
	
	private void calculateintakePerDay() {
		double bodyFactor = 1;
		switch(bodyShape) {
		case "overweight" :		bodyFactor *=0.8; break;
		case "underweight" :	bodyFactor *=1.4; break;
		case "ideal" :			bodyFactor *=1.2; break;
		}
		
		switch(activeLevel) {
		case "spayed" :			bodyFactor *=1.1;
		case "neutered" :		bodyFactor *=1.1;
		case "intact" :			bodyFactor *=1.2;
		}
		
		if(age>1) {
			bodyFactor *=1;
		}else {
			if(age<0.09)
			{
				bodyFactor = (2.5-(10*(age-0.04)));
			}else if(age >= 0.09) {
				bodyFactor = (2.15-(10*(2.2-(age-0.08))));
			}
		}
		TMR = (int) (BMR*bodyFactor);
		intakePerDay = (int) (TMR*1000/currFoodCalories);
	}
	
	private void calculateBMR() {
		this.BMR = (int) Math.pow(catWeight, 0.75);
		this.BMR = BMR*70;
	}
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getBreeder() {
		return breeder;
	}
	public void setBreeder(String breeder) {
		this.breeder = breeder;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	public double getCatWeight() {
		return catWeight;
	}
	public void setCatWeight(double catWeight) {
		this.catWeight = catWeight;
	}
	public String getBodyShape() {
		return bodyShape;
	}
	public void setBodyShape(String bodyShape) {
		this.bodyShape = bodyShape;
	}
	public String getActiveLevel() {
		return activeLevel;
	}
	public void setActiveLevel(String activeLevel) {
		this.activeLevel = activeLevel;
	}
	public int getBMR() {
		return BMR;
	}
	public void setBMR(int bMR) {
		BMR = bMR;
	}
	public int getIntakePerDay() {
		return intakePerDay;
	}
	public void setIntakePerDay(int intakePerDay) {
		this.intakePerDay = intakePerDay;
	}

	public String getCurrFoodName() {
		return currFoodName;
	}

	public void setCurrFoodName(String currFoodName) {
		this.currFoodName = currFoodName;
	}

	public double getCurrFoodCalories() {
		return currFoodCalories;
	}

	public void setCurrFoodCalories(int currFoodCalories) {
		this.currFoodCalories = currFoodCalories;
	}
	
	public int getTMR() {
		return TMR;
	}
}
