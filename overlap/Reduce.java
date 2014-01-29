import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class Reduce  extends Reducer<IntWritable, Text, IntWritable, Text>
{
	public void reduce(IntWritable key, Iterable<Text> values, Context contxt )throws IOException, InterruptedException
	{
		HashMap<String,Integer> filecount = new HashMap<String,Integer>();
		ValueComparator bvc =  new ValueComparator(filecount);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
		for(Text filetext : values)
		{
			String[] files = filetext.toString().split("/") ;
			String  file = files[files.length-1];  
			
			if(filecount.containsKey(file ))
			{
				int value = filecount.get(file ) + 1;
				//filecount.remove(file );
				filecount.put(file , value);
			}
			else
			{
				filecount.put(file.toString(), 1);
			}
			
		}
		sorted_map.putAll(filecount);
		Iterator<String>  keys =sorted_map.keySet().iterator();
		StringBuilder file_freq = new StringBuilder();
		while(keys.hasNext())
		{
			String key_ =  keys.next();
			file_freq.append(key_+ ":" + filecount.get(key_)+" ");
		}
		contxt.write(key, new Text( file_freq.toString()));
		//if
	}
}

class ValueComparator implements Comparator<String> {

    HashMap<String, Integer> base;
    public ValueComparator(HashMap <String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
