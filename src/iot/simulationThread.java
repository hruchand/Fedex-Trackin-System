/*
 * This is the main simulation thread which runs infinitely after it's instantiation in main class.
 * Mainly it is responsible for updating the hashmap of 100000 in realtime.Considertion here is that
 * packet travels from one city to another in 15sec(real time) irrespective of the distance.
 * Note:This thread executes in background and updates our main hashmap in real time irrespective of 
 * instantiation of the query thread  
 * 
 */
package iot;
import java.util.ArrayList;
import java.util.Date;
public class simulationThread implements  Runnable {
	Main main = new Main();
	Thread thread = new Thread(this);
	int id;
	public simulationThread()
	{
		thread.start();
	}
	public void run() {
		ArrayList<Integer> shpa = new ArrayList<>();
		int cur_ct_id;
		int city_count =0;
		packetDetails packet= new packetDetails();
		try{
			while(true){
				for(int j=0;j<25;j++){
					for(int i = 0;i< Main.main_map.size();i++){
						packet  =  Main.main_map.get(i);
						shpa = packet.sp;
						city_count = shpa.size();
						if(j<city_count){
							Date date = new Date();
							cur_ct_id = shpa.get(j);
							packet.curr_city_str.add(Main.cityname_map.get(cur_ct_id));
							packet.Status = ("In Transit");
							packet.date_time = date.toString();
							Main.main_map.put(i, packet);
						}
						if(j==city_count-1)
						{
							Date date = new Date();
							packet.Status = ("Delivered");
							packet.date_time = date.toString();
						}
					}
					thread.sleep(15000);
				}
				break;
			}
		}
		catch (Exception e) {
		}
	}


}