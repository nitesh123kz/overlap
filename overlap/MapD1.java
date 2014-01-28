import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class MapD1  extends Mapper<LongWritable, Text, Text, Text> 
{
	private Text word = new Text();
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		//reading D1.txt and  split into array of lines
		String doc	= value.toString();
		String[] lines =  doc.split("\n");
		for ( String line : lines)
		{
			word.set(line);
			context.write(word, new Text("D1"));
		}
	}
	
	
}
