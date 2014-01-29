import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
public class MapD1  extends Mapper<LongWritable, Text, IntWritable, Text> 
{
	private Text word = new Text();
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String path_ = ((FileSplit)context.getInputSplit()).getPath().toString();
		String doc	= value.toString();
		String[] Keys =  doc.split("&");
		int key_=0;
		try
		{
			 key_ =  Integer.parseInt(Keys[0].split("=")[1]);
		}
		catch (Exception e)
		{
			System.exit(0);
		}
		//for ( String line : lines)
		//{
			word.set(doc);
			context.write(new IntWritable(key_),new Text(path_));
		//l}
	}
	
	
}
