/*
 * This class has the declaration of our main hashmap.
 * Functionality in this class:
 * 1. Creation of packets
 * 2. Calculation of the shortest path using dijkstras_Shortest_Path class.
 * 3. Instantiation of simulation thread and query thread.
 * 4. Creation of cityname_map(25 cities).
 * 5. Declaration of route (valid paths of cities)---> Hard coded & weight free (no distance consideration)
 * 6. Scanning the input from user(TrackingID)  
 */
package iot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
	static HashMap<Integer, String> cityname_map = new HashMap<>();
	HashMap<Integer, packetDetails> map_populate = new HashMap<>();
	public static HashMap<Integer, packetDetails> main_map = new HashMap<>();
	ArrayList<Integer> shortest_Path = new ArrayList<>();
	static int trackingId=0;
	public static void main(String[] args) {
		Main main = new Main();
		try
		{
			main.populate_ct_names();
			main_map = main.generate_packets();	
			main_map = main.load_shortest_paths(main_map);
			simulationThread packageThread = new simulationThread();		
			while(true)
			{
				Scanner scanner = new Scanner(System.in);
				if(scanner.hasNext())
				{
					trackingId = scanner.nextInt();
					Query_t query = new Query_t(trackingId);
				}

			}

		}
		catch (Exception e) {
			System.out.println("inside exception");
			System.out.println(e.getMessage());
		}
	}
	HashMap<Integer, packetDetails> generate_packets()
	{
		for(int i = 0; i <100000; i ++)
		{
			packetDetails packets =new packetDetails(i);
			map_populate.put(i, packets);
		}
		return map_populate;
	}
	ArrayList<Integer> matrixCreation(int sourceNode, int destinationNode)
	{
		try
		{
			int[] source= {0,1,2,3,4,12,12,12,11,11,11,11,11,14,23,23,23,23,3,5,5,5,5};
			int[] destination = {4,4,4,4,12,10,13,11,15,14,16,17,18,23,19,20,21,22,5,6,7,8,9};
			int source_Node=sourceNode, destination_Node=destinationNode;
			int[][] adjacentMatrix = new int[Main.cityname_map.size()][Main.cityname_map.size()];
			Dijkstras_Shortest_Path shortestPath = new Dijkstras_Shortest_Path();
			double[][] adjMatrix = new double[Main.cityname_map.size()][Main.cityname_map.size()]; 
			int[][] matrix = new int[Main.cityname_map.size()][Main.cityname_map.size()];
			for(int row = 0; row < Main.cityname_map.size(); row++ )
			{
				for(int col = 0; col< Main.cityname_map.size(); col++)
				{
					if(row == col)
					{
						adjMatrix[row][col] = 0;
						matrix[row][col] = 0;
					}
					else 
					{
						matrix[row][col] = (int)adjMatrix[row][col];
					}
				}
			}
			for(int i = 0;i< Main.cityname_map.size();i++)
			{
				for(int j=0; j <Main.cityname_map.size();j++)
				{
					adjacentMatrix[i][j]=0;
				}
			}
			for(int i = 0 ; i<Main.cityname_map.size(); i++)
			{
				int source_city = i;
				ArrayList<Integer> destintaion_cities= new ArrayList<>();
				for(int j = 0; j <source.length; j++)
				{
					if(source[j]==source_city)
					{
						destintaion_cities.add(destination[j]);
					}
				}
				for(int j = 0; j <source.length; j++)
				{
					if(destination[j]==source_city)
					{
						destintaion_cities.add(source[j]);
					}
				}
				int countOfDestCities = destintaion_cities.size();

				for(int k = 0; k <countOfDestCities; k++)
				{
					adjacentMatrix[i][destintaion_cities.get(k)] = 1;
				}

			}


			for(int i = 0;i< Main.cityname_map.size();i++)
			{
				for(int j=0; j <Main.cityname_map.size();j++)
				{
					adjacentMatrix[i][j]=adjacentMatrix[j][i];
				}

			}


			shortest_Path=	shortestPath.dijkstra(adjacentMatrix,source_Node, destination_Node);

		}
		catch (Exception e) {
			System.out.println("Exception occurred");

		}
		return shortest_Path;
	}
	HashMap<Integer,packetDetails> load_shortest_paths(HashMap<Integer,packetDetails> map){
		packetDetails p = new packetDetails();
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0;i< map.size();i++){
			p  =  map.get(i);
			list = matrixCreation(p.source, p.dest);
			p.sp = list;
			map.put(i, p);
		}
		return map;
	}
	public void populate_ct_names(){
		cityname_map.put(0, "Northborough, Ma");
		cityname_map.put(1, "Edison, Nj");
		cityname_map.put(2, "pittsburgh, Pa");
		cityname_map.put(3, "Allentown, Pa");
		cityname_map.put(4, "Martinsburg, Wv");
		cityname_map.put(5, "Charlotte, Nc");
		cityname_map.put(6, "Atlanta, Ga");
		cityname_map.put(7, "Orlando, Fl");
		cityname_map.put(8, "Memphis, Tn");
		cityname_map.put(9, "GroveCity, Oh");
		cityname_map.put(10, "Indianapolis, In");
		cityname_map.put(11, "Detroit, Mi");
		cityname_map.put(12, "NewBerlin, Wi");
		cityname_map.put(13, "Minneapolis, Mn");
		cityname_map.put(14, "StLouis, Mo");
		cityname_map.put(15, "Kansas, Ks");
		cityname_map.put(16, "Dallas, Tx");
		cityname_map.put(17, "Houston, Tx");
		cityname_map.put(18, "Denver, Co");
		cityname_map.put(19, "saltLakeCity, Ut");
		cityname_map.put(20, "phoenix, Az");
		cityname_map.put(21, "LosAngeles, Ca");
		cityname_map.put(22, "Chino, Ca");
		cityname_map.put(23, "Sacramanto, Ca");
		cityname_map.put(24, "Seattle, Wa");

	}
}

