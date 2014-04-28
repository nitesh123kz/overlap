package com.paypal.mpi;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.mapreduce.Mapper;

import com.paypal.fpti.event.validation.metadata.FPTIEnum;
import com.paypal.fpti.sessionization.model.Event;
import com.paypal.fpti.sessionization.model.VisitCommon;
import com.paypal.fpti.sessionization.model.VisitContainer;
import com.paypal.fpti.sessionization.model.VisitKey;


public class CampaignMetricMapper extends Mapper<VisitKey,VisitContainer,CampaignKey,CampaignEvent>
{
	 
	public void map(VisitKey key, VisitContainer vc,Context context)throws IOException,InterruptedException
	{
		if(key==null||vc==null)
			return;
		List<Event> events = vc.getEvents();
		for(Event event : events)
		{
			context.getCounter("CAMPAIGNMETRIC_STAGE1","TOTAL_EVENTS").increment(1);
			String enrich = event.getData(FPTIEnum.ENRICH_DATA);
			
			if (enrich!=null && enrich.equalsIgnoreCase("n")) 
			{  
				
				System.out.println("Server side : \n"+event.toString());
				
				context.getCounter("CAMPAIGNMETRIC_STAGE1", "SERVERSIDE_EVENT").increment( 1);
				String srce = event.getData(FPTIEnum.DATA_SOURCE);
				if (srce !=null && srce.equals("mpi_legacy"))
				{
					context.getCounter("CAMPAIGNMETRIC_STAGE1", "MPI_LEGACY").increment( 1);
					
					CampaignEvent ev = new CampaignEvent();
					VisitCommon vcom = vc.getVisitCommon();
					ev.browser = vcom.getData(FPTIEnum.BROWSER_TYPE);
					if(ev.browser==null)
						ev.browser="";
					ev.device  = vcom.getData(FPTIEnum.DEVICE_TYPE);
					if(ev.device==null)
						ev.device="";
					ev.geoState = vcom.getData(FPTIEnum.GEO_REGION);
					if(ev.geoState==null)
						ev.geoState="";
					ev.os =  vcom.getData(FPTIEnum.CLIENT_OS);
					if(ev.os==null)
						ev.os="";
					ev.event = event.getData(FPTIEnum.EVENT_TYPE);
					if(ev.event==null)
						ev.event="";
					CampaignKey ckey = new CampaignKey();
					ckey.setCampaignID(event.getData(FPTIEnum.INT_CAMPAIGN_CODE));
					 
					Map<String, String> data = event.getM_parameterMap();
					String offer = data.get("oid");
					ckey.setOfferID(offer);
					
					
					ckey.setOfferVID(event.getData(FPTIEnum.OFFER_VERSION_ID));
							
					context.write(ckey, ev);
				//break;		
				}
				
			}
			else
			{

				System.out.println("Client side : \n"+event.toString());
				context.getCounter("CAMPAIGNMETRIC_STAGE1", "CLIENT_SIDE").increment(1);
			}
		}
		
	}
}
