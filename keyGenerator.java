package key;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class keyGenerator {
public static void main(String args[]) 
{
	File file = new File("data1.txt");
	FileWriter fr = null;
	try {
        fr = new FileWriter(file);
	ArrayList<Integer> Key1 = new ArrayList<Integer>();
	for(int i=0;i<10;i++)
	{	
		Key1.add(i);
	}
	ArrayList<String> Key2 = new ArrayList<String>();
	for(char i='a';i<'k';i++)
	{	
		Key2.add(""+i);
	}
	ArrayList<String> Key3 =new ArrayList<String>();
	for(char i='k';i<'u';i++)
	{	
		Key3.add(""+i);
	}
	ArrayList<Integer> Key4=new ArrayList<Integer>();
	for(int i=100;i<110;i++)
	{	
		Key4.add(i);
	}
	ArrayList<Integer> Key5=new ArrayList<Integer>();
	for(int i=15;i<25;i++)
	{	
		Key5.add(i);
	}
	for(int i=0;i<10;i++)
	{
		fr.write("k1="+Key1.get(i)+"&k2="+Key2.get(i)+"&k3="+Key3.get(i)+"&k4="+Key4.get(i)+"&k5="+Key5.get(i)+"\n");
	}
	fr.close();
	}
	catch(IOException e) {
        e.printStackTrace();
	}
}
	
}
