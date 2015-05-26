/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 26 May 2015
 */
import java.io.Serializable;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TrainingData implements Serializable{
	ArrayList<DataSet> list = new ArrayList<DataSet>();
	public TrainingData()
	{
		loadData();
	}
	/**
	 * [delete removes the selected dataset]
	 * @param index [index of set to remove]
	 */
	public void delete(int index)
	{
		list.remove(index);
		saveData();
	}
	/**
	 * [addData add the data to the list for processing]
	 * @param fileName [Name of the file containing the text]
	 * @param lang     [AFR/ENG the language of the text]
	 * @param title    [A tilte of the text]
	 */
	public void addData(String fileName,String lang,String title)
	{
		DataSet tmp = new DataSet(lang,title);
		try {
			List<String> lines = Files.readAllLines(Paths.get("trainingData/" + fileName));
			System.out.println("File contents");
			System.out.println("==============================================");
			for(String line : lines)
			{
				System.out.println(line);
			}
			System.out.println("==============================================");
			list.add(tmp);
			saveData();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	
	}
	/**
	 * [print displays the current data]
	 */
	public void print()
	{
		Integer index = 0;
		System.out.println("==============================================");
		for(DataSet set : list)
		{
			System.out.println("Index: " + index);
			System.out.println("Title: " + set.title);
			System.out.println("Language: " + set.lang);
			index++;
		}
		System.out.println("==============================================");

	}
	/**
	 * [saveData saved the data to a file. So that it will be optimuzed when training begins.]
	 */
	public void saveData()
	{
		try{
			OutputStream file =  new FileOutputStream("langData.ser");
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(list);
			output.close();
			System.out.println("The object was saved to a file.");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * [loadData loads optimized data from the file.]
	 */
	public void loadData()
	{
		try{
			InputStream file = new FileInputStream("langData.ser");
	      	InputStream buffer = new BufferedInputStream(file);
	      	ObjectInput input = new ObjectInputStream (buffer);
	      	list = (ArrayList<DataSet>)input.readObject();
			System.out.println("The object was loaded from a file.");
		}
		catch(ClassNotFoundException ex){
      		ex.printStackTrace();
    	}	
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	/*
	This class holds the data for each text that you add to the training data. 
	After the data has been processed it is added to the list and the stored in memory.
	 */
	class DataSet implements Serializable{
		public String lang;
		public String title;
		public Hashtable<Character,Integer> inputCharacters = new Hashtable<Character,Integer>();
		/**
		 * [DataSet This data set holds the data for each piece of text added]
		 * @param  lang  [The language of the text ENGLISH or AFRIKAANS]
		 * @param  title [The title of the text. Such as book name ect.]
		 * @return       [description]
		 */
		public DataSet(String lang,String title){
			this.title = title;
			this.lang = lang; 
			/*
			Here we initialize the dataset to a empty set of alphabet characters.
			 */
			for(int i = ((int)'a');i <= ((int)'z');i++){
				//System.out.println((char)i);
				inputCharacters.put((char)i,0);
			}
			//printContent();
		}
		/**
		 * [printContent displays the conent in the DataSet]
		 */
		public void printContent(){
			System.out.println("==============================================");
			System.out.println("Language: " + lang);
			System.out.println("Title: " + title);
			for(int i = ((int)'a');i <= ((int)'z');i++){
				System.out.println(((char)i) +" count is "+ inputCharacters.get((char)i));
			}
			System.out.println("==============================================");
		}

	}
}