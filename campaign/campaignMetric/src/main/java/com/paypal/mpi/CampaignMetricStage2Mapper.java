package com.paypal.mpi;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;

public class CampaignMetricStage2Mapper extends Mapper<CampaignKey,CampaignEvent,CampaignKey,CampaignEvent >
{
	
	//private MultipleOutputs multiple;
	public void setup(Context context)throws java.io.IOException ,
	InterruptedException
	{
		// multiple = new  MultipleOutputs(context);
	}
	
	
	public void map(CampaignKey key, CampaignEvent ev, Context context) throws IOException, InterruptedException
	{
		
		context.write(key, ev);
		
	}

}
