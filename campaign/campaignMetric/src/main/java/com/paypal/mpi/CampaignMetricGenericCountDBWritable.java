package com.paypal.mpi;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class CampaignMetricGenericCountDBWritable implements Writable, DBWritable{
	private String CampaignID;
	private String OfferID;
	private String  OfferVID;
	private String timestamp;
	private String browser;
	private String device;
	private String OS;
	private String location;
	private int count;
	private int im_count;
	private int cl_count;
	private int cv_count;
	public  CampaignMetricGenericCountDBWritable(String CampaignID,String OfferID, String OfferVID, String timestamp,String browser,String device, String OS, String location,int im_count,int cl_count, int cv_count, int count)
	{
		this.CampaignID=CampaignID;
		this.OfferID = OfferID;
		this.OfferVID = OfferVID;
		this.timestamp = timestamp;
		
		this.browser = browser;
		this.device = device;
		this.OS= OS;
		this.location= location;
		this.count = count;
		this.im_count=im_count;
		this.cl_count=cl_count;
		this.cv_count=cv_count;
		 
	}
	@Override
	public void readFields(ResultSet in) throws SQLException {
		CampaignID = in.getString(1);
		OfferID = in.getString(2);
		OfferVID = in.getString(3);
		timestamp = in.getString(4);
		browser = in.getString(5);
		device = in.getString(6);
		OS =  in.getString(7);
		location = in.getString(8);
		im_count = in.getInt(9);
		cl_count = in.getInt(10);
		cv_count = in.getInt(11);
		count = in.getInt(12);
		
		
	}

	@Override
	public void write(PreparedStatement statement) throws SQLException {
		
		statement.setString(1,CampaignID);
		statement.setString(2, OfferID);
		statement.setString(3,OfferVID);
		statement.setString(4,timestamp);
		statement.setString(5,browser);
		statement.setString(6,device);
		statement.setString(7, OS );
		statement.setString(8,location);
		statement.setInt(9,im_count);
		statement.setInt(10,cl_count);
		statement.setInt(11, cv_count);
		statement.setInt(12,count);
		
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
