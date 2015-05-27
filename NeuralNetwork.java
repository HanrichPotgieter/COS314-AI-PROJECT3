/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 27 May 2015
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.lang.Math.*;

public class NeuralNetwork{
	Double high = 0.7;
	Double low = 0.3;
	Integer input = 4;
	Integer hidden = 4;
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

	ArrayList<Integer> ak = new ArrayList<Integer>();

	Random random = new Random();

	public NeuralNetwork()
	{
		/**
		 * Setup of neural network and linking all edges.
		 */
		//Create input nodes
		for(int i = 0;i < input;i++){
			zi.add(new Node());
		}
		//Creat Bias node and add it.
		Node bias = new Node();
		bias.value = -1.0;
		zi.add(bias);
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
		//Creat Bias node and add it.
		bias = new Node();
		bias.value = -1.0;
		yj.add(bias);
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
		/**
		* Initializing the weights of all the edges.
		*/
		for(Node y:yj){
			Integer fanin = y.inputEdges.size();
			for(Edge e:y.inputEdges){
				e.weight = random(fanin);
			}
		}
		for(Node o:ok){
			Integer fanin = o.inputEdges.size();
			for(Edge e:o.inputEdges){
				e.weight = random(fanin);
			}
		}
		startValues();
		feedForwardPhase();
		printNetwork();
		
	}
	public void feedForwardPhase()
	{
		//Hidden layer
		//Compute the netyi
		for(Node y : yj){
			Double netyj = 0.0;
			for(Edge e : y.inputEdges){
				netyj += e.weight * e.startNode.value;
				System.out.println(netyj);
			}
			y.value = sigmoid(netyj);
		}
		//Output layer
		//Compute the netyi
		for(Node o : ok){
			Double netok = 0.0;
			for(Edge e : o.inputEdges){
				netok += e.weight * e.startNode.value;
				System.out.println(netok);
			}
			o.value = sigmoid(netok);
			// Calculating the accuracy of each output node.
			if(o.value >= high)
				o.ak = 1;
			else if(o.value <= low)
				o.ak = 0;
			else
				o.ak = -1;
		}
		
	}

	public void startValues()
	{
		for(Node n:zi){
			n.value = 1.0;
		}
	}
	public Double sigmoid(Double net)
	{
		return (1/(1+Math.pow((Math.E),-net)));
	}
	public Double random(Integer fanin){
		Double start = -((1)/(Math.sqrt(fanin)));
	//	System.out.println(start);
		Double end = ((1)/(Math.sqrt(fanin)));
	//	System.out.println(end);
		Double range = (end - start);
	//	System.out.println(range);
		Double fraction = range * random.nextDouble();
		Double randomNumber = fraction + start;
	//	System.out.println(fraction);
	//	System.out.println(randomNumber);
		return randomNumber;
	}
	public void printNetwork(){
		System.out.println("Input nodes");
		System.out.println("==============================================");
		for(Node n: zi){
			System.out.println("Value: " + n.value);
			System.out.println("inputEdges: " + n.inputEdges.size());
			System.out.println("outputEdges: " + n.outputEdges.size());
		}
		System.out.println("==============================================");
		System.out.println("Hidden nodes");
		for(Node n:yj){
			System.out.println("Value: " + n.value);
			System.out.println("inputEdges: " + n.inputEdges.size());
			System.out.println("outputEdges: " + n.outputEdges.size());
		}
		System.out.println("==============================================");
		System.out.println("Output nodes");
		for(Node n:ok){
			System.out.println("Value: " + n.value);
			System.out.println("inputEdges: " + n.inputEdges.size());
			System.out.println("outputEdges: " + n.outputEdges.size());
			System.out.println("Ak: " + n.ak);

		}
		System.out.println("==============================================");
		System.out.println("Vji Edges");
		System.out.println(vji.size());
		System.out.println("==============================================");
		System.out.println("Wkj Edges");
		System.out.println(wkj.size());
		System.out.println("==============================================");
	}
}