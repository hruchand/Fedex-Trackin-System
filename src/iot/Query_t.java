package iot;
/*
 * This is query thread which runs when user requests the tracking details of any  particular packet.
 * Tracking id is passed to get the required info from the hashmap.
 * This thread is instantiated in Main class and start() method in this class starts the thread.
 * Constraint: tracking id needs to be in range 0 to 99999
 */
public class Query_t implements  Runnable {
	Thread thread = new Thread(this);
	int id;

	public Query_t(int id)
	{
		thread.start();
		this.id = id;
	}
	public void run() {
		System.out.println("Fedex Courier Tracking System");
		System.out.println("-----------------------------");
		System.out.println("Source: "+ Main.cityname_map.get(Main.main_map.get(id).source));
		System.out.println("Destination: "+ Main.cityname_map.get(Main.main_map.get(id).dest));
		System.out.println("Dimension: "+Main.main_map.get(id).length+"x"+Main.main_map.get(id).width+"x"+Main.main_map.get(id).height);
		System.out.println("Tracking ID: "+ Main.main_map.get(id).trackingId);
		System.out.println("Current city: "+Main.main_map.get(id).curr_city_str);
		System.out.println("Status: "+Main.main_map.get(id).Status);
		System.out.println("Date & Time "+Main.main_map.get(id).date_time);
		System.out.println("\n");

	}


}
