package main;

import java.util.Random;
import java.util.Scanner;

public class Main {

	
	static Random random = new Random();
	static int infected = 0;
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		timeStep timeStepClass = new timeStep();
			
		System.out.println("Enter the number of individuals:");
		int N = scnr.nextInt();
		
		while (Math.sqrt(N) != (int)Math.sqrt(N)) {
			System.out.println("Sorry that won't work. Please enter a number that is a perfect square:");
			N = scnr.nextInt();
		}
		
		System.out.println("Enter the number of time steps:");
		int T = scnr.nextInt();
		
		System.out.println("Enter the infection rate:");
		double iRate = scnr.nextDouble();
		
		while (iRate<0 || iRate>1) {
			System.out.println("That did not work. Please enter an infection rate that is equal to 0 or 1, or between 0 and 1:");
			iRate = scnr.nextDouble();
		}
		
		System.out.println("Enter the recovery rate:");
		double rRate = scnr.nextDouble();
		
		while (rRate<0 || rRate>1) {
			System.out.println("That did not work. Please enter a recovery rate that is equal to 0 or 1, or between 0 and 1:");
			rRate = scnr.nextDouble();
		}
		
		
		String [][] individuals = new String[(int) Math.sqrt(N)][(int) Math.sqrt(N)];
		
		for (int i=0; i<individuals.length; i++) {
			for (int j=0; j<individuals[i].length; j++) {
				individuals[i][j] = "S";
			}
		}
		
		int rand1 = random.nextInt((int) Math.sqrt(N));
		int rand2 = random.nextInt((int) Math.sqrt(N));
		
		individuals[rand1][rand2] = "I";
		
		timeStepClass.setNumIndividuals(N);
		timeStepClass.setNumTimeSteps(T);
		timeStepClass.setInfectionRate(iRate);
		timeStepClass.setRecoveryRate(rRate);
		
		
		for (int i=0; i<individuals.length; i++) {
			for (int j=0; j<individuals[i].length; j++) {
				
				
				if (i==0 && j==0) {
					updateTopLeft(individuals, iRate, rRate);
				}
				
				
				if (i==0 && j==individuals.length-1) {
					updateTopRight(individuals, iRate, rRate, i, j);
				}
				
				if (i==individuals.length-1 && j==0) {
					updateBottomLeft(individuals, iRate, rRate, i, j);
				}
				
				if (i==individuals.length-1 && j==individuals.length-1) {
					updateBottomRight(individuals, iRate, rRate, i, j);
				}
				
				
				if (j==0 && i>0 && i<individuals.length-1) {
					updateLeftColumn(individuals, iRate, rRate, i, j);
				}
				
				if (i==0 && j>0 && j<individuals.length-1) {
					updateTopRow(individuals, iRate, rRate, i, j);
				}
				
				if (j==individuals.length-1 && i>0 && i<individuals.length-1) {
					updateRightColumn(individuals, iRate, rRate, i, j);
				}
				
				if (i==individuals.length-1 && j>0 && j<individuals.length-1) {
					updateBottomRow(individuals, iRate, rRate, i, j);
				}
				
				if (i>0 && i<individuals.length-1 && j>0 && j<individuals.length-1) {
					updateMiddle(individuals, iRate, rRate, i, j);
				}
				
				
				
			}
			
		}
		
		
		
		
		
		for (int i=0; i<individuals.length; i++) {
			System.out.println();
			for (int j=0; j<individuals[i].length; j++) {
				System.out.print(individuals[i][j]);
			}
		}

	}
	
	
	
	static void updateMiddle(String[][] individuals, double iRate, double rRate, int i, int j) {
		
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[i][j-1]=="I") {
				infected++;
			}
			
			if (individuals[i][j+1]=="I") {
				infected++;
			}
			
			if (individuals[i-1][j]=="I") {
				infected++;
			}
			
			if (individuals[i+1][j]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}
		
		
	}
	
	
	static void updateBottomRow(String[][] individuals, double iRate, double rRate, int i, int j) {
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[individuals.length-1][j-1]=="I") {
				infected++;
			}
			
			if (individuals[individuals.length-1][j+1]=="I") {
				infected++;
			}
			
			if (individuals[individuals.length-2][j]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}	
	}
	
	
	
	
	static void updateRightColumn(String[][] individuals, double iRate, double rRate, int i, int j) {
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[i-1][j]=="I") {
				infected++;
			}
			
			if (individuals[i+1][j]=="I") {
				infected++;
			}
			
			if (individuals[i][j-1]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}	
	}
	
	
	
	
	static void updateTopRow(String [][] individuals, double iRate, double rRate, int i, int j) {
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[0][j-1]=="I") {
				infected++;
			}
			
			if (individuals[0][j+1]=="I") {
				infected++;
			}
			
			if (individuals[1][j]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}
		
	}
	
	
	
	
	static void updateLeftColumn (String[][] individuals, double iRate, double rRate, int i, int j) {
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[i-1][0]=="I") {
				infected++;
			}
			
			if (individuals[i+1][0]=="I") {
				infected++;
			}
			
			if (individuals[i][1]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}
		
	}
	
	
	static void updateTopLeft(String individuals[][], double iRate, double rRate) {
		
		if (individuals[0][0] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[0][0] = "R";
			}
			
		}
		
		if (individuals[0][0]=="S") {
			if (individuals[0][1]=="I") {
				infected++;
			}
			
			if (individuals[1][0]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[0][0] = "I";
			}
			
			infected = 0;
		}
		
	}
	
	
	static void updateTopRight(String individuals[][], double iRate, double rRate, int i, int j) {
		
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[0][individuals.length-2]=="I") {
				infected++;
			}
			
			if (individuals[1][individuals.length-1]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}
		
		
		
	}
	
	
	static void updateBottomLeft(String individuals[][], double iRate, double rRate, int i, int j) {
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[individuals.length-1][1]=="I") {
				infected++;
			}
			
			if (individuals[individuals.length-2][0]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}
		
	}
	
	
	static void updateBottomRight(String[][] individuals, double iRate, double rRate, int i, int j) {
		
		if (individuals[i][j] == "I") {
			boolean isRecovered = randRec(rRate);
			
			if (isRecovered == true) {
				individuals[i][j] = "R";
			}
			
		}
		
		if (individuals[i][j]=="S") {
			if (individuals[individuals.length-1][individuals.length-2]=="I") {
				infected++;
			}
			
			if (individuals[individuals.length-2][individuals.length-1]=="I") {
				infected++;
			}
			
			boolean is_Infected = randInf(iRate, infected);
			
			if (is_Infected==true) {
				individuals[i][j] = "I";
			}
			
			infected = 0;
		}
		
	}
	
	static boolean randRec(double rRate) {
		boolean isRecovered = false;
		double rand = random.nextDouble(1);
		
		if (rand<=rRate) {
			isRecovered = true;
		} else {
			isRecovered = false;
		}
		
		return isRecovered;
	}
	
	static boolean randInf(double iRate, int infected) {
		boolean isInfected = false;
		double totalInfRate = iRate*infected;
		double rand = random.nextDouble(1);
		
		if (totalInfRate >= 1) {
			isInfected = true;
		} else if (rand<=totalInfRate) {
			isInfected = true;
		} else {
			isInfected = false;
		}
		
		return isInfected;
	}
}




/*
if (i==0 && j==individuals[0].length-1) {
	
}

if (i==individuals.length-1 && j==0) {
	
}

if (i==individuals.length-1 && j==individuals[i].length-1) {
	
}
*/


/*
call the do time step method which includes
writing the status of every individual to a file.
Then make a for loop in this file which for the
rest of the time steps calls another function that has the 
do time step code but with getting the status of every individual
from the previous file.
*/

// also figure out a way of checking neighbors status.
// Some people are in corners. Some are not.
//timeStepClass.doTimeStep(individuals, T);


// System.out.println(rand1 + " " + rand2);

/*
for (int i=0; i<individuals.length; i++) {
	System.out.println();
	for (int j=0; j<individuals[i].length; j++) {
		System.out.print(individuals[i][j]);
	}
}
*/


/*
for (int i=0; i<individuals.length; i++) {
	System.out.println();
	for (int j=0; j<individuals[i].length; j++) {
		if ((i==0 && j==0) || (i==0 && j==individuals[0].length-1) || (i==individuals.length-1 && j==0) || (i==individuals.length-1 && j==individuals[i].length-1)) {
			System.out.print("C");
		} else {
			System.out.print("N");

		}
	}
}
*/