package com.paypal.mpi;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
//import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

//public class CampaignMetricStage2Reducer extends Reducer<CampaignKey,Iterable<CampaignEvent>,CampaignMetricGenericCountDBWritable,NullWritable>
public class CampaignMetricStage2Reducer extends Reducer<CampaignKey,Iterable<CampaignEvent>,Text,Text>
{
	private Map<String,Integer> countMap  =new HashMap<String,Integer>();
	private  Map<String,Map<String,Integer>> map = new HashMap<String,Map<String,Integer>>();
	
	private String browser;
	private String device;
	private String OS;
	private String location;
	private String timestamp;
	private int  im_count;
	private int  cl_count;
	private int  cv_count;
	
	private MultipleOutputs multiple;
	public void setup(Context context)throws java.io.IOException ,
	InterruptedException
	{
		 String period = context.getConfiguration().get("period");
		 String[] periods =  period.split("/");
		 String time = periods[0]+"-"+periods[1]+"-"+periods[2]+" "+periods[3]+":00:00";
		 
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date;
		 
		 multiple = new  MultipleOutputs(context);
		  
		try {
			date = formatter.parse(time);
			timestamp = formatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
	}

	public void reduce(CampaignKey key,Iterable<CampaignEvent> Events ,Context context ) throws IOException,InterruptedException
	{ 
		
		for(CampaignEvent event: Events)
		{
			browser= event.browser;
			if(browser==null)
				browser = "UNKNOWN";
			
			device = event.device;
			if(device==null)
				device = "UNKNOWN";
			
			OS = event.os;
			if (OS == null)
				OS = "UNKNOWN";
			
			location = event.geoState;
			if(location ==null)
				location = "UNKNOWN";
			StringBuilder build = new StringBuilder();
			List<String> list = new LinkedList<String>();
			list.add(browser);build.append(browser);
			list.add(device);build.append("-"+device);
			list.add(OS);build.append("-"+OS);
			list.add(location);build.append("-"+location);
			
			
			if( map.containsKey(build.toString() ))
			{
				System.out.println(list);
				Map<String,Integer> count_map=map.get(build.toString());
				int count = count_map.get("count");
				count++;
				count_map.put("count", count);
				int imcount = count_map.get("im_count");
				imcount+= im_count;
				count_map.put("im_count", imcount);
				int clcount = count_map.get("cl_count");
				clcount+= cl_count;
				count_map.put("cl_count", clcount);
				int cvcount = count_map.get("cv_count");
				cvcount+= cv_count;
				count_map.put("cv_count", cvcount);
				
				
				
				map.put(build.toString(),count_map);
			}
			else
			{
				Map<String,Integer>   count_map = new HashMap<String,Integer>();
				count_map.put("count", 1);
				count_map.put("im_count", im_count);
				count_map.put("cl_count",cl_count);
				count_map.put("cv_count", cv_count);
				map.put(build.toString(),count_map);
			}
					  
		}
		//multiple.write("completed", new Text((new Integer(map.entrySet().hashCode())).toString()), new Text(new Integer(map.entrySet().size()).toString()));
		for(Map.Entry<String,Map<String,Integer> > entry: map.entrySet())
		{
			
			//context.getCounter(entry.getKey().toString(), entry.getValue().toString()).increment(1);
			
			multiple.write("DBOutput",DBObject(key,entry), NullWritable.get());
			
			
		}
		
	}
	
	protected void cleanup(Context context) throws IOException, InterruptedException {
        multiple.close();
   }
	private CampaignMetricGenericCountDBWritable DBObject(CampaignKey key, Entry<String,Map<String,Integer>>  entry)
	{
		String[] list = entry.getKey().split("-");
		Map<String,Integer> value =  entry.getValue();
		
		CampaignMetricGenericCountDBWritable CMGCDBW = new CampaignMetricGenericCountDBWritable(key.getCampaignID(),key.getOfferID(),key.getOfferVID(),timestamp,list[0],list[1],list[2],list[3],value.get("im_count"),value.get("cl_count"),value.get("cv_count"),value.get("count")   );
		return CMGCDBW;
	}
 }
