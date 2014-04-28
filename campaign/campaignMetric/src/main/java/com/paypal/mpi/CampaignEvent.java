package com.paypal.mpi;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.codehaus.jackson.map.ObjectMapper;

public class CampaignEvent implements  Writable
{
	String  event="";
	String  browser="";
	String  os="";
	String  geoState="";
	String  device="";//String country
	
	
	 
	@Override
	public void readFields(DataInput in) throws IOException {
		event =  Text.readString(in);
		browser =  Text.readString(in);
		os =  Text.readString(in);
		geoState = Text.readString(in);
		device = Text.readString(in);
		
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		Text.writeString(out,event);
		Text.writeString(out,browser);
		Text.writeString(out,os);
		Text.writeString(out,geoState);
		Text.writeString(out,device);
		
	}
	
	public String toString(){
		return toJson();
	}

	public String toJson() {
		try {
			StringBuilder buf = new StringBuilder();
			buf.append("\"CampaignEvent\" : ");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectMapper om = new ObjectMapper();
			Map<String,String> m_parameterMap = new LinkedHashMap<String,String>();
			m_parameterMap.put("event",event);
			m_parameterMap.put("browser",browser);
			m_parameterMap.put("os",os);
			m_parameterMap.put("geoState",geoState);
			m_parameterMap.put("device",device);
			
			
			om.writeValue(baos, m_parameterMap);
			buf.append(new String(baos.toByteArray(), "UTF-8"));
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	 

}
