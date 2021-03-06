/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 26 May 2015
 */

import java.util.Scanner;

public class main{
	public static void main(String[] args){
		TrainingData trainingData = new TrainingData("langData.ser");
		TrainingData unseenData = new TrainingData("langDataUnseen.ser");
		NeuralNetwork neuralNetwork = null;
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
			else if(option == 4)
			{
				System.out.println("Creating neural network");
				//System.out.print("Please enter amount of hidden nodes:");
				//int hidden = in.nextInt();
				neuralNetwork = new NeuralNetwork();
				System.out.println("Type c to continue");
				in.next();

			}
			else if(option == 5)
			{
				trainingData.print();
				System.out.print("Enter the index to be used for training:");
				int index = in.nextInt();
				DataSet data = trainingData.getData(index);
				if(neuralNetwork != null){
					neuralNetwork.startValues(data);
					neuralNetwork.start();
				}
				else{
					System.out.println("Error: pleae initilize the neural network.");
				}
				System.out.println("Type c to continue");
				in.next();
			}
			else if(option == 6)
			{
				System.out.println("Please enter the ammount of hidden nodes:");
				Integer hidden = in.nextInt();
				System.out.println("Please enter the learing rate:");
				Double learningRate =  in.nextDouble();
				System.out.println("Please enter the momentum:");
				Double momentum =in.nextDouble();
				System.out.println("Please enter the max Epoch:");
				Integer maxEpoch = in.nextInt();

				if(neuralNetwork != null){
					neuralNetwork.hidden = hidden;
					neuralNetwork.learningRate = learningRate;
					neuralNetwork.momentum = momentum;
					neuralNetwork.maxEpoch = maxEpoch;
					neuralNetwork.trainSet(trainingData.list);
				}
				else{
					System.out.println("Error: pleae initilize the neural network.");
				}
			
				System.out.println("Type c to continue");
				in.next();

			}
			else if(option == 7)
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
				unseenData.addData(filename,lang,title);
				System.out.println("Type c to continue");
				in.next();
			}
			else if(option == 8)
			{
				unseenData.print();
				System.out.print("Specify index to see:");
				int index = in.nextInt();
				unseenData.print(index);
				System.out.println("Type c to continue");
				in.next();
			}
			else if(option == 9)
			{
				unseenData.print();
				System.out.print("Enter the index to delete:");
				int index = in.nextInt();
				unseenData.delete(index);
			}
			else if(option == 10)
			{
				unseenData.print();
				System.out.print("Enter the index of data to run:");
				int index = in.nextInt();
				neuralNetwork.runSet(unseenData.list.get(index));
				System.out.println("Type c to continue");
				in.next();
			}
		}
	}
	public static void printMenu()
	{
		System.out.println("(1) Add training data.");
		System.out.println("(2) Display training data.");
		System.out.println("(3) Delete training data.");
		System.out.println("(4) Create neural network.");
		System.out.println("(5) Train neural network.");
		System.out.println("(6) Train on multiple data sets.");
		System.out.println("(7) Add unseen data.");
		System.out.println("(8) Display unseen data.");
		System.out.println("(9) Delete unseen data.");
		System.out.println("(10) Run unseen data");
		System.out.println("(99) Quit.");
	}
	public final static void clearConsole()
	{
		System.out.print("\033[H\033[2J"); 
	}
}

