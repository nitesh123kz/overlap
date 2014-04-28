package com.paypal.mpi;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.io.*;

//public class CampaignMetricReducer  extends Reducer<CampaignKey,CampaignEvent,CampaignKey,CampaignMetric>
public class CampaignMetricReducer  extends Reducer<CampaignKey,CampaignEvent,CampaignKey,CampaignEvent>
{
	private String campaign_id="";
	private String offer_id="";
	private String offer_vid="";
	private String timestamp="";
	@SuppressWarnings("rawtypes")
	private MultipleOutputs multiple;
	 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	public void reduce(CampaignKey key,  Iterable<CampaignEvent>  events, Context context)
	throws java.io.IOException,InterruptedException	
	{
		
		
		campaign_id =  key.getCampaignID();
		
		offer_id  =  key.getOfferID();
		
		offer_vid =  key.getOfferVID();
		
		CampaignMetric cMetric = new CampaignMetric();
		cMetric.clear();
		cMetric.campaign_id = campaign_id;
		cMetric.offer_id =  offer_id;
		cMetric.offer_vid  = offer_vid; 
		cMetric.timestamp  = timestamp;
		cMetric.im_count=0;
		cMetric.cl_count=0;
		cMetric.cv_count=0;
		
		for(CampaignEvent event : events)
		{ System.out.println("entering event");
			/*if( cMetric.browser_count.containsKey(  event.browser))
			{
				 int value = cMetric.browser_count.get(event.browser);
				 value++;
				 cMetric.browser_count.put(event.browser, value);
			}
			else
			{
				cMetric.browser_count.put(event.browser, 1);
			}
			
			if( cMetric.os_count.containsKey(  event.os))
			{
				 int value = cMetric.os_count.get(event.os);
				 value++;
				 cMetric.os_count.put(event.os, value);
			}
			else
			{
				cMetric.os_count.put(event.os, 1);
			}
			
			if( cMetric.geo_state_count.containsKey(  event.geoState))
			{
				 int value = cMetric.geo_state_count.get(event.geoState);
				 value++;
				 cMetric.geo_state_count.put(event.geoState, value);
			}
			else
			{
				cMetric.geo_state_count.put(event.geoState, 1);
			}
			
			if( cMetric.device_count.containsKey(  event.device))
			{
				 int value = cMetric.device_count.get(event.device);
				 value++;
				 cMetric.device_count.put(event.device, value);
			}
			else
			{
				cMetric.device_count.put(event.device, 1);
			}*/
			
			if(event.event.equals("im"))
			{
				cMetric.im_count++;
			}
			else if(event.event.equals("cl"))
			{
				cMetric.cl_count++;
			}
			else if(event.event.equals("cv"))
			{
				cMetric.cv_count++;
			}
			multiple.write("completed",key ,event);
		}
		
		System.out.println("exiting event");
		//context.write (key, cMetric);
		multiple.write("DBOutput",cMetricToCampaignEventCountDBWritable(cMetric) ,NullWritable.get());
		System.out.println("dboutput");
		
		
		
		
	}
	 
	
	protected void cleanup(Context context) throws IOException, InterruptedException {
         multiple.close();
    }

	private CampaignEventCountDBWritable cMetricToCampaignEventCountDBWritable(CampaignMetric cM)
	{
		CampaignEventCountDBWritable CEVDBW = new CampaignEventCountDBWritable(cM.campaign_id,cM.offer_id, cM.offer_vid, cM.timestamp, cM.im_count, cM.cl_count, cM.cv_count);
		return CEVDBW;
	}
}
