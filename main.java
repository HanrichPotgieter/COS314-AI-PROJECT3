/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 26 May 2015
 */

import java.util.Scanner;

public class main{
	public static void main(String[] args){
		TrainingData trainingData = new TrainingData();
		Scanner in = new Scanner(System.in);
		while (true) {
			clearConsole();
			System.out.println("==============================================");
			System.out.println("Neural Network - Language Recognition Software");
			System.out.println("Author: Hanrich Potgieter");
			System.out.println("==============================================");
			printMenu();	
			System.out.print("Please select a option:");
			Integer option = in.nextInt();
			if(option == 99)
				break;
			else if(option == 1)
			{
				String filename;
				String lang;
				String title;
				System.out.print("Please enter file name: ");
				filename = in.next();
				System.out.print("Please enter language(ENG/AFR): ");
				lang = in.next();
				System.out.print("Please enter title: ");
				title = in.next();
				trainingData.addData(filename,lang,title);
				System.out.println("Type c to continue");
				in.next();
			}
			else if(option == 2)
			{
				trainingData.print();
				System.out.print("Specify index to see:");
				int index = in.nextInt();
				trainingData.print(index);
				System.out.println("Type c to continue");
				in.next();
			}
			else if(option == 3)
			{
				trainingData.print();
				System.out.print("Enter the index to delete:");
				int index = in.nextInt();
				trainingData.delete(index);
			}
		}
	}
	public static void printMenu()
	{
		System.out.println("(1) Add training data.");
		System.out.println("(2) Display training data.");
		System.out.println("(3) Delete training data.");
		System.out.println("(99) Quit.");
	}
	public final static void clearConsole()
	{
		System.out.print("\033[H\033[2J"); 
	}
}

