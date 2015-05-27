/**
 * Author: Hanrich Potgieter
 * Student Number: 12287343
 * Date: 27 May 2015
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Node{
	public Double value = null;
	public ArrayList<Edge> inputEdges = new ArrayList<Edge>();
	public ArrayList<Edge> outputEdges = new ArrayList<Edge>();
	public Integer ak;
	public Double error;
}