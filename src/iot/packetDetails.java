/*
 * This class contains all the attributes associated with packets  such as source,destination,weight,length
 * height etc.
 * As the source and destinations are randomly generated there may be some corner cases, such as source 
 * is same as destination. These cases are also taken care of here.
 */
package iot;
import java.util.ArrayList;
import java.util.Random;
public class packetDetails {
	int dest = 0;
	int length = 0;
	int width = 0;
	int height = 0;
	int weight=0;
	int trackingId=0 ;
	int source = 0;
	String Status = "";
	String date_time = "";
	ArrayList<Integer> sp = new ArrayList<>();
	ArrayList<String> curr_city_str = new ArrayList<>();
	Random random = new Random();
	public packetDetails(){
	}
	public packetDetails(int trackingId)
	{
		this.trackingId = trackingId;
		width = random.nextInt(20)+5;
		height = random.nextInt(20)+5;
		weight = random.nextInt(30)+6;
		length = random.nextInt(15)+5;
		this.source =random.nextInt(24); 
		this.dest = random.nextInt(24);

		if(this.source != this.dest)
		{
	     //do nothing
		}
		else
		{
			if(this.source == 0)
				this.source = this.source+1;
			else
				this.source = this.source -1;

		}
	}
}






