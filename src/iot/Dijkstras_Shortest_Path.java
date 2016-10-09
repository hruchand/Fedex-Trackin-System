/*
 * This class is used to calculate the path from source to destination. Although this function works for 
 * weighted scenarios this code doesn't use that. Hence, this function calculates the path from source
 * to destination. 
 */
package iot;
import java.util.*;
class Dijkstras_Shortest_Path
{
	static final int noc=25;
	int minDistance(int dist[], Boolean sptSet[])
	{
		int min = Integer.MAX_VALUE, min_index=-1;
		for (int v = 0; v < noc; v++)
			if (sptSet[v] == false && dist[v] <= min)
			{
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}
	ArrayList<Integer> arrayList = new ArrayList<>();
	ArrayList<Integer> printSolution(int dist[], int n,int nodePath[],int src, int dest )
	{
		for (int i = 0; i < n; i++){
			if(dest==i)
			{
				arrayList=printPath(nodePath,i);
			}
		}
		return arrayList;
	}
	ArrayList<Integer> list = new ArrayList<>();
	ArrayList<Integer> printPath(int nodePath[], int j)
	{
		if(nodePath[j]==-1)
		{
			return list;
		}
		printPath(nodePath,nodePath[j]);
		list.add(j);
		return list;	
	}
	ArrayList<Integer> path = new ArrayList<>();
	ArrayList<Integer> dijkstra(int graph[][], int source, int dest)
	{
		int destination = dest;
		int dist[] = new int[noc]; 
		Boolean sptSet[] = new Boolean[noc];
		int nodePath[]=new int[noc];
		for (int i = 0; i < noc; i++)
		{
			nodePath[source]=-1;
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		dist[source] = 0;
		for (int count = 0; count < noc-1; count++)
		{
			int u = minDistance(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < noc; v++)
				if (!sptSet[v] && graph[u][v]!=0 &&
				dist[u] != Integer.MAX_VALUE &&
				dist[u]+graph[u][v] < dist[v]){
					nodePath[v]=u;
					dist[v] = dist[u] + graph[u][v];
				}
		}
		path =  printSolution(dist, noc,nodePath,source, destination);
		ArrayList<Integer> A=new ArrayList<>();
		A.add(source);
		for(int j=0;j<path.size();j++)
		{
			A.add(path.get(j)) ;
		}
		return A;
	}
}