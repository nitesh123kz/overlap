import java.nio.ByteBuffer;

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
			super(IntWritable.class );
		}
		@Override
		public int compare(Object arg0, Object arg1)
		{
			Integer arga  =  ((IntWritable)arg0).get();
			Integer argb  =  ((IntWritable)arg1).get();
			return -1*arga.compareTo(argb);
		}
		
		@Override
		public int compare(byte[] raw1, int offset1, int length, byte[] raw2,
				int offset2, int length2) {
			Integer int1  =  ByteBuffer.wrap(raw1,offset1,length).getInt();
			Integer int2  =  ByteBuffer.wrap(raw2,offset2, length2).getInt();
			return int2.compareTo(int1);
		}

	}
	public static class  DescendingIntWritable2 implements RawComparator<IntWritable>
	{

		@Override
		public int compare(IntWritable arg0, IntWritable arg1)
		{
			Integer arga  =  arg0.get();
			Integer argb  =  arg1.get();
			return -1*arga.compareTo(argb);
		}

		@Override
		public int compare(byte[] arg0, int arg1, int arg2, byte[] arg3,
				int arg4, int arg5) {
			// TODO Auto-generated method stub
			return 1;
		}

		
		
		
	}

	@Override
	public int run(String[] args) throws Exception
	{
	   // When implementing tool
        Configuration conf = new Configuration();
       // Create job
       // @SuppressWarnings("deprecation")
       // setting  job  config
	   Job job = new Job(conf);
       job.setJarByClass(DriverOverlap.class); 
       job.setReducerClass(Reduce.class);
       job.setMapperClass(MapD1.class);
       job.setOutputKeyClass(IntWritable .class);
       job.setOutputValueClass(Text.class);
       job.setSortComparatorClass(DescendingIntWritable.class);	
       FileInputFormat.setInputPaths(job, new Path(args[0]), new Path(args[1]));
       FileOutputFormat.setOutputPath(job,new Path(args[2]));
       
       return  job.waitForCompletion(true)?0:1;
		
	}

}
