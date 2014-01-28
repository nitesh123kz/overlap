import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class Reduce  extends Reducer<Text, IntWritable, Text, IntWritable>
{
//	private static  Map<String, String>  IsExisting = new HashMap<String,String>();
	public void reduce(Text key, Iterable<Text> values, Context contxt )throws IOException, InterruptedException
	{
		ArrayList<Text> temp_value = new ArrayList<Text>();
		for (Text value : values)
		{
			temp_value.add(value);
		}
		if ( temp_value.contains(new Text("D1")) &&  temp_value.contains(new Text("D2")))
		{
			contxt.write(key, null);
		}
	}
}
