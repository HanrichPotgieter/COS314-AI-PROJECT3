/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 26 May 2015
 */
import java.io.Serializable;
import java.util.Hashtable;
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