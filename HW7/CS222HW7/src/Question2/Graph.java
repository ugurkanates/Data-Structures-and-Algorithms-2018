package Question2;

import java.util.ArrayList;


public class Graph {


	public static ArrayList<Edge>[] adjacencyList=null;
	public static int[] inEdgeCount=null;
	public int noOfVertices;


	public Graph(int noOfVertices)
	{
		adjacencyList=(ArrayList<Edge>[])new ArrayList[noOfVertices+1];
		//TO initialize with empty arrayList
		for(int i=1;i<=noOfVertices;i++)
		{
			adjacencyList[i]=new ArrayList<Edge>();
		}
		inEdgeCount=new int[noOfVertices+1];
		this.noOfVertices=noOfVertices;
	}

	/*
	 * To add edges to the adjacency list graph
	 */
	public void addEdge(int u, int v, int w)
	{
		if(adjacencyList[u]==null)
			adjacencyList[u]=new ArrayList<Edge>();
		adjacencyList[u].add(new Edge(u, v, w));
	}

	/*
	 * To remove the edge from the graph
	 */
	public void removeEdge(int u, int v)
	{
		int indexToBeRemoved=-1;
		ArrayList<Edge> edgeList=adjacencyList[u];
		for(int i=0;i<adjacencyList[u].size();i++)
		{
			Edge e=edgeList.get(i);
			if(e.u==u&&e.v==v)
			{
				indexToBeRemoved=i;
			}
		}
		if(indexToBeRemoved!=-1)
			edgeList.remove(indexToBeRemoved);
	}

	/*
	 *
	 * To return the outgoing edges for the given source
	 */
	public ArrayList<Edge> getOutEdges(int u)
	{
		return adjacencyList[u];
	}

	/*
	 * To get the weight given the u and v values
	 * 
	 */
	public int getWeight(int u, int v)
	{
		ArrayList<Edge> edgeList=adjacencyList[u];
		int weight=0;
		for(Edge e : edgeList)
		{
			if(e.v==v)
				weight=e.w;
		}
		return weight;
	}

	/*
	 * To return the array of adjacency list(Actually the graph)
	 */
	public ArrayList<Edge>[] getListOfAdjacencylist()
	{
		return adjacencyList;
	}

	/*
	 * To return the count of incoming edges for each vertices in an array
	 *
	 */
	public int[] getInEdgesCount()
	{
		for(int i=1;i<=noOfVertices;i++)
		{
			for(int j=1;j<adjacencyList.length;j++)
			{
				ArrayList<Edge> list=adjacencyList[j];
				for(Edge e : list)
				{
					if(e.v==i)
						inEdgeCount[i]++;
				}
			}

		}
		return inEdgeCount;
	}



}
