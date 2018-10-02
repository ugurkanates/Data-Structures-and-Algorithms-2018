package Question1;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by paypaytr on 5/28/18.
 */
public class Question1 {
    public static boolean isCyclic(Graph obje) {
        int len = obje.noOfVertices;
        boolean[] isVisited = new boolean[len];
        for(int i =0; i<len; i++) {
            isVisited[i] = false;
        }

        Stack s = new Stack<>();
        s.add(0);

        while(!s.isEmpty()) {
            int current = (int) s.pop();
            System.out.println(current);
            isVisited[current] = true;
            for(int i =0; i<len; i++) {
                ArrayList<Edge> edgeList;
                edgeList= obje.adjacencyList[i];
                    if (!isVisited[i]) {
                        if (s.contains(i)) {
                            return false;
                        }
                        s.push(i);
                        break;
                    }

            }



        }
        return true;

    }
    private String spaceReturner(int i) {
        StringBuilder sb = new StringBuilder();
        for(int j=0;j<i;j++)
            sb.append(" ");
        return sb.toString();

    }
    private String weightReturnerReturner(int i) {
        StringBuilder sb = new StringBuilder();
        for(int j=0;j<i;j++)
            sb.append("-");
        return sb.toString();

    }
    public void plotGraph(Graph graph)
    {
        ArrayList<Edge> edgeList;
        ArrayList<Boolean>edgeListTravelled = new ArrayList<Boolean>(graph.noOfVertices+1);
        for(int i=1;i<graph.noOfVertices+1;i++){
            edgeListTravelled.add(false);
        }

        for(int i=1;i<graph.noOfVertices;i++)
        {
            edgeList= Graph.adjacencyList[i];
            for(Edge e : edgeList)
                System.out.println(" "+e.u+" -> "+e.v+" Edge Weight: "+e.w);
        }
        for(int i=1;i<graph.noOfVertices;i++) {
            edgeList = Graph.adjacencyList[i];
            if (!edgeListTravelled.get(i-1)) {
                for (int j = 0; j < edgeList.size(); j++) {
                    System.out.println(spaceReturner(i)+ edgeList.get(j).u
                            +weightReturnerReturner(graph.getWeight(edgeList.get(j).u,edgeList.get(j).v)%10+1)+"> " + edgeList.get(j).v);


                }
                edgeListTravelled.set(i,true);
            }
        }
    }

    public static void main(String[] args) {
        Question1 obje = new Question1();
        Graph graph=new Graph(7);

        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 4, 2);
        graph.addEdge(5, 7, 3);
        graph.addEdge(3, 6, 4);
        graph.addEdge(6, 7, 5);
        graph.addEdge(4, 7, 6);
        graph.addEdge(6, 5, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 9);

        obje.plotGraph(graph);

        DAGShortestPath daobje = new DAGShortestPath();
        daobje.findShortestPath(graph,1,5);
        System.out.println();
        daobje.findShortestPath(graph,3,4);
        System.out.println();
        daobje.findShortestPath(graph,6,7);
        System.out.println();
        System.out.println("CHECKING IF ACYCLIC ->");
        System.out.println(isCyclic(graph));

    }
}
