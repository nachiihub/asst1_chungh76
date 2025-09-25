import java.util.InputMismatchException;
import java.util.Scanner;

public class asst1_chungh76 {
	
	//Checks the user's input for any errors
	public boolean error_check(double user_input, double lower_bound, double upper_bound, boolean turn) {
		boolean status = true;
		//Checks user input for errors for coil diameter and wire diameter inputs
		if (turn == false) {
			if (user_input < 0) {
				status = false;
				System.out.println("ENTER A POSITIVE INPUT");
				return status;
			}
		
			if (lower_bound > user_input || user_input > upper_bound) {
				status = false;
				System.out.println("INPUT MUST BE WITHIN BOUNDS ");
				return status;
			}
		}
		
		//Checks user input for errors for number of turns
		if (turn == true) {
			if(user_input <= 0){
				status = false;
				System.out.println("N SHOULD BE A POSITIVE INTEGER");
				return status;
			}
			if (lower_bound > user_input || user_input > upper_bound) {
				status = false;
				System.out.println("INPUT MUST BE WITHIN BOUNDS ");
				return status;
			}
		}

		return status;
	}
	
	//Returns the user's input for either coil_diameter or wire diameter
	public double getValidInput(Scanner scanner, double num_input, boolean coil_d, boolean wire_d) {
		boolean valid = false;
		boolean error = false;
		num_input = 0;
		
		//Gets valid input for when asking for coil diameter
		if (coil_d == true) {
			while(!valid) {
				//Uses try and catch to check if the input is a valid double
				try {
					num_input = scanner.nextDouble();
					valid = true;
				}
				catch (InputMismatchException e){
					System.out.println("ENTER A VALID INPUT");
					scanner.nextLine();
					System.out.print("Enter coil diameter D (m): ");
				}
			}
			// Checks if the input is within bounds and positive
			while(error == false) {
				error = error_check(num_input, 0.25, 1.3, false);
				if (error == true){
					break;
				}
				// Reprompts the user for input if there is an error
				System.out.print("Enter coil diameter D (m): ");
				if (error == false) {
					num_input = scanner.nextDouble();
				}
			}
		}
		
		//Gets valid input for when asking for wire diameter
		if (wire_d == true) {
			while(!valid) {
				//Uses try and catch to check if the input is a valid double
				try {
					num_input = scanner.nextDouble();
					valid = true;
				}
				catch (InputMismatchException e){
					System.out.println("ENTER A VALID INPUT");
					scanner.nextLine();
				}
			}
			// Checks if the input is within bounds and positive
			while(error == false) {
				error = error_check(num_input, 0.05, 2, false);
				if (error == true){
					break;
				}
				// Reprompts the user for input if there is an error
				System.out.print("Enter wire diameter d (m): ");
				if (error == false) {
					num_input = scanner.nextDouble();
				}
			}
		}	
		return num_input;
	}
	
	//Returns the user's input for number of turns
	public int getValidInput(Scanner scanner, int num_input, boolean turns) {
			boolean valid = false;
			boolean error = false;
			num_input = 0;		
			//Gets valid input for when asking for number of turns
			if (turns == true) {
				while(!valid) {
					//Uses try and catch to check if the input is a valid integer
					try {
						num_input = scanner.nextInt();
						valid = true;
					}
					catch (InputMismatchException e){
						System.out.println("N SHOULD BE AN INTEGER");
						scanner.nextLine();
					}
				}
				// Checks if the input is within bounds and positive
				while(error == false) {
					error = error_check(num_input, 1, 15, true);
					if (error == true){
						break;
					}
					// Reprompts the user for input if there is an error
					System.out.print("Enter number of turns N: ");
					if (error == false) {
						num_input = scanner.nextInt();
					}
				}
				return num_input;
			}
			return num_input;
		}
	
	//Calculate the weight based on the given parameters
	public double WeightCalculation(double coil_d, double wire_d, int turn_num) {
		double weight = 0; //Instantiate the weight variable
		double gravity = 9.81; //Instantiate the gravity variable
		double mass = (turn_num + 2)*coil_d * Math.pow(wire_d, 2); //Calculate the mass using m(D,d,N) = (N+2)*D*d^2
		weight = mass * gravity;
		double truncated_weight = Math.floor(weight * 100) / 100;
		return truncated_weight;
	}
	
	//Prompt the question and check if the answer to the question is a valid input
	public boolean Question_Prompt(Scanner scanner) {
		boolean status = false;
		while (status == false) {
			System.out.print("WELCOME TO THE SPRING WEIGHT CALCULATOR (0 TO QUIT, 1 TO PROCEED) ");
			int user_choice = scanner.nextInt();
			//Checks if the user input is valid
			if (user_choice == 1) { //If the user inputs 1 then the program will proceed
				status = true;
				break;
			}
			if (user_choice == 0) { //If the user inputs 0 then the program will end
				status = false;
				break;
			}
			/*
			If the user inputs anything other than 0 or 1 then it will send an error 
			message and reprompt the question
			*/
			System.out.println("ENTER A VALID INPUT");
		}
		return status;
	}
	
	//Used to display the final answer
	public void display_answer(double weight) {
		System.out.println("Weight: " + weight + " kgm/s^2");
		System.out.println("Goodbye");
	}
		
	
	//Main Method
	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);  // Instantiate the scanner object
		asst1_chungh76 main = new asst1_chungh76();
		boolean status = main.Question_Prompt(scanner);
		//if the user selects 1 then the question will prompt if not the code will end
		if(status== true) {
			System.out.print("Enter coil diameter D (m): ");
			double coil_diameter = main.getValidInput(scanner,0.0, true, false);
			System.out.print("Enter wire diameter d (m): ");
			double wire_diameter = main.getValidInput(scanner, 0, false, true);
			System.out.print("Enter number of turns N: ");
			int num_turn = (int) main.getValidInput(scanner, 0, true);
			double weight = main.WeightCalculation(coil_diameter, wire_diameter, num_turn);
			main.display_answer(weight);
		}
	}
	
}
