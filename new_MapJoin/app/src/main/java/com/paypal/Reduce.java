package com.paypal;
import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class Reduce  extends Reducer<Text, Text, Text, Text>
{
	private String input_file1,input_file2;
	private int arg_3;
	private HashMap<String,LinkedList<String>>  store =  new  HashMap<String,LinkedList<String>>();
	
	public void setup(Context context)
	{
		//arg_1 = Integer.parseInt(context.getConfiguration().get("arg1"));
		arg_3 =  Integer.parseInt(context.getConfiguration().get("arg3"));
		String[] input_path1split = context.getConfiguration().get("path1").split("/");
		String[] input_path2split = context.getConfiguration().get("path2").split("/");
		input_file1 =  input_path1split[input_path1split.length-1];
		input_file2 =  input_path2split[input_path2split.length-1]; 
		
		
	}
	public void reduce(Text key, Iterable<Text> values, Context contxt )throws IOException, InterruptedException
	{
		store.put(input_file1, new LinkedList<String>());
		store.put(input_file2, new LinkedList<String>());
		//StringBuilder  strin=new StringBuilder();
		for(Text value : values)
		{
			//strin.append(value.toString()+"\n");
			String list =  value.toString();
			String file = list.split(":")[0];
			if(file.equals(input_file1))
			{
				LinkedList<String> temp=store.get(input_file1);
				temp.add(list.split(":")[1]);
				store.put(input_file1, temp);
			}
			else if(file.equals(input_file2))
			{
				LinkedList<String> temp=store.get(input_file2);
				temp.add(list.split(":")[1]);
				store.put(input_file2, temp);
			}
		}
		//contxt.write(key, new Text(strin.toString()));
		LinkedList<String> input_1_list =  store.get(input_file1);
		LinkedList<String> input_2_list =  store.get(input_file2);
		if(input_1_list.size()>=1 &&input_2_list.size()>=1)
		{
		
			StringBuilder  output_ =  new  StringBuilder();
		for( String line_1 : input_1_list)
		{
			StringBuilder  output  = new   StringBuilder();
			for (String line_2 : input_2_list)
			{
				StringBuilder  line=  new  StringBuilder(line_1);
				String[] line_2split =  line_2.split("&");
				for(int i=0;i<line_2split.length;i++)
				{
					if(i!=arg_3)
					{
						line.append("&"+line_2split[i]);
					}
				}
				output.append(line.toString()+"\n");
			}
			
			output_.append(output);
			//contxt.write(key,new Text(line.toString()));
		}
		contxt.write(new Text(key.toString()),new Text(output_.toString()));
		
		}		
	}
}

