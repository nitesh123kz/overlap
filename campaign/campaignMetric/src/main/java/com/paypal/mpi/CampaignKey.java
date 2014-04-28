package com.paypal.mpi;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class CampaignKey  implements WritableComparable<CampaignKey>
{
	
	private  String  campaignID ;
	private  String  offerID ;
	private String offerVID;
	public void clear(){
		campaignID = "";
		offerID = "";
		offerVID = "";
		 
	}
	
	public String getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}

	public String getOfferID() {
		return offerID;
	}

	public void setOfferID(String offerId) {
		this.offerID = offerId;
	}
	
	public String getOfferVID() {
		return offerVID;
	}

	public void setOfferVID(String offerVID) {
		this.offerVID = offerVID;
	}

	@Override
	 public void readFields(DataInput in) throws IOException {

			campaignID = Text.readString(in);
			offerID= Text.readString(in); 
			offerVID= Text.readString(in); 
			
			 
		}
 
	@Override
	public void write(DataOutput out) throws IOException {
		Text.writeString(out, campaignID);
		Text.writeString(out, offerID);
		Text.writeString(out, offerVID);	
		 
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("CampaignID=")
		   .append(campaignID)
		   .append("&OfferID=")
		   .append(offerID)
		   .append("&OfferVID=")
		   .append(offerVID);;
		
		return buf.toString();
	}
	
	@Override
	public int hashCode() {
		return campaignID.hashCode()+ 17*offerID.hashCode() + 17*17*offerVID.hashCode() ;
	}

	@Override
	public int compareTo(CampaignKey o) {
		int result = campaignID.compareTo(o.campaignID);
		if (result == 0) {
			result = offerID.compareTo(o.offerID);
		}
		if (result == 0) {
			result = offerVID.compareTo(o.offerVID);
		}
		 
		
		return result;
	}

}
