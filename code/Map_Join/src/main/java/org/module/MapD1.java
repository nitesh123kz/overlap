package org.module;
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
public class MapD1  extends Mapper<LongWritable, Text, Text, Text> 
{
	//private Text word = new Text();
	private int arg_1, arg_3;
	String input_file1,input_file2;
	
	public void setup(Context context)
	{
		arg_1 = Integer.parseInt(context.getConfiguration().get("arg1"));
		arg_3 =  Integer.parseInt(context.getConfiguration().get("arg3"));
		 String[] input_path1split = context.getConfiguration().get("path1").toString().split("/");
		 String[] input_path2split = context.getConfiguration().get("path2").toString().split("/");
		 input_file1 =  input_path1split[input_path1split.length-1];
		 input_file2 =  input_path2split[input_path1split.length-1];
		
	}
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String current_file = ((FileSplit)context.getInputSplit()).getPath().getName().toString();
		String rec	= value.toString();
		String[] key_value_pairs =  rec.split("&");
		String  key_value_pair = new String();
		if(current_file.equals(input_file1))
		{
			 key_value_pair =  key_value_pairs[arg_1];
		}
		else if(current_file.equals(input_file2))
		{
			 key_value_pair =  key_value_pairs[arg_3];
		}
		String map_val =  key_value_pair.split("=")[1];
		context.write(new Text(map_val), new Text(current_file+":"+rec));
	}	
}