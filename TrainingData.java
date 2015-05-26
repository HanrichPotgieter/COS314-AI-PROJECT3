/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 26 May 2015
 */
import java.io.Serializable;
import java.util.Hashtable;
import java.util.ArrayList;
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


public class TrainingData implements Serializable{
	DataSet a = new DataSet("ENGLISH","Mary had a little lamb");
	ArrayList<DataSet> list = new ArrayList<DataSet>();
	public TrainingData()
	{
		
		loadData();
	}
	public void addData(String fileName,String lang,String title)
	{
		DataSet tmp = new DataSet(lang,title);
		saveData();
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