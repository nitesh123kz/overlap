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
       job.setOutputKeyClass(Text.class);
       job.setOutputValueClass(Text.class);
       MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,MapD1.class);
       MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat .class,  MapD2.class);
       FileOutputFormat.setOutputPath(job,new Path(args[2]));
       
       return  job.waitForCompletion(true)?0:1;
		
	}

}
