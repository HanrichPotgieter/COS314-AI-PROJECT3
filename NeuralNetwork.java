/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 27 May 2015
 */

import java.util.ArrayList;
import java.util.Iterator;

public class NeuralNetwork{
	Integer input = 26;
	Integer hidden = 26;
	Integer output = 2;
	Double learningRate = 0.0;
	Double momentum = 0.0;
	Integer epoch = 1;
	Integer maxEpoch = 10;
	//Input Layer
	ArrayList<Node> zi = new ArrayList<Node>();
	ArrayList<Edge> vji = new ArrayList<Edge>();
	//Hidden Layer
	ArrayList<Node> yj = new ArrayList<Node>();
	ArrayList<Edge> wkj = new ArrayList<Edge>();
	//Output layer
	ArrayList<Node> ok = new ArrayList<Node>();

	public NeuralNetwork()
	{
		//Create input nodes
		for(int i = 0;i < input;i++){
			zi.add(new Node());
		}
		//Create hidden nodes
 		for(int i = 0;i < hidden;i++){
			yj.add(new Node());
		}
		//Create Edges Vji
		for(Node y : yj){
			for(Node z: zi){
				//create edge
				Edge tmp = new Edge();
				tmp.startNode = z;
				tmp.endNode = y;
				vji.add(tmp);
				//Add Edge to node
				z.outputEdges.add(tmp);
				y.inputEdges.add(tmp);
			}
		}
		//Create output nodes
 		for(int i = 0;i < output;i++){
			ok.add(new Node());
		}
		//Create Edges Wkj
		for(Node o : ok){
			for(Node y : yj){
				//Create Edge
				Edge tmp = new Edge();
				tmp.startNode = y;
				tmp.endNode = o;
				wkj.add(tmp);
				//Add edge to node
				y.outputEdges.add(tmp);
				o.inputEdges.add(tmp);
			}
		}
		
	}
}