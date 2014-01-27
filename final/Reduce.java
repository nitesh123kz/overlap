import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class Reduce  extends Reducer<Text, Text, Text, NullWritable>
{
//	private static  Map<String, String>  IsExisting = new HashMap<String,String>();
	public void reduce(Text key, Iterable<Text> values, Context contxt )throws IOException, InterruptedException
	{
		ArrayList<String> temp_value = new ArrayList<String>();

		for (Text value : values)
		{
		      temp_value.add(value.toString());
			
		}
	        if ( temp_value.contains("D1") &&  temp_value.contains("D2"))
		{
			contxt.write(key, null);
		}
	}
}
