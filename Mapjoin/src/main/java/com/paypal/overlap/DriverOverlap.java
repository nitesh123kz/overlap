package com.paypal.overlap;
import java.nio.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*; 
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class DriverOverlap  extends Configured  implements Tool
{
	
	public static void main(String args[]) throws Exception
	{
		 int res = ToolRunner.run(new Configuration(), new DriverOverlap(), args);
	     System.exit(res);	
	}
	public static class DescendingIntWritable extends WritableComparator
	{

		public DescendingIntWritable()
		{
			super(Text.class );
		}
		@Override
		
		public int compare(Object arg0, Object arg1)
		{
			String arga  = arg0.toString();
			String argb  = arg1.toString();
			return -1*arga.compareTo(argb);
		}
		
		@Override
		public int compare(byte[] raw1, int offset1, int length, byte[] raw2,
				int offset2, int length2) {
			CharBuffer int1  =  ByteBuffer.wrap(raw1,offset1,length).asCharBuffer();
			CharBuffer int2  =  ByteBuffer.wrap(raw2,offset2, length2).asCharBuffer();
			String key1=int1.toString();
			String key2=int2.toString();
			return key2.compareTo(key1);
		}
	}



	@Override
	public int run(String[] args) throws Exception
	{
	   // When implementing tool
        Configuration conf = new Configuration();
        conf.set("arg1", args[1]);
        conf.set("arg3", args[3]);
        conf.set("path1",args[0]);
        conf.set("path2",args[2]);
       // Create job
       // @SuppressWarnings("deprecation")
       // setting  job  config
	   Job job = new Job(conf);
	  
       job.setJarByClass(DriverOverlap.class); 
       job.setReducerClass(Reduce.class);
       job.setMapperClass(MapD1.class);
       job.setOutputKeyClass(Text .class);
       job.setOutputValueClass(Text.class);
       job.setSortComparatorClass(DescendingIntWritable.class);	
       FileInputFormat.setInputPaths(job, new Path(args[0]), new Path(args[2]));
       FileOutputFormat.setOutputPath(job,new Path(args[4]));
       
       return  job.waitForCompletion(true)?0:1;
		
	}

}
