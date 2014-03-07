import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File.*;
public class keygenerator {
	public static void main(String args[]) throws IOException
	{
		//List 1
		ArrayList<Integer> Key1 = new ArrayList<Integer>();
		for(int i=0;i<10;i++)
			Key1.add(i);
		
		//List 2
		LinkedList<String> Key2 = new LinkedList<String>();
		for(int i=6;i<9;i++)
			for(char a='a';a<'k';a++)
				Key2.add(Integer.toString(i)+""+a);
		
		//List 3
		ArrayList<Integer> Key3 = new ArrayList<Integer>();
		for(int i=1000;i<1020;i++)
			Key3.add(i);
		
		//List 4
		LinkedList<String>  Key4 = new LinkedList<String>();
		for(int i=24;i<28;i++)
			for(char a='q';a>'l';a--)
			{//System.out.println("List 4 ");
				Key4.add(""+a+""+Integer.toString(i));
			}
//System.out.println("List 4:"+Key4);
		
		//List 5
		
		LinkedList<String> Key5 = new  LinkedList<String>();
		for(char a = 't';a<'y';a++)
			for(char b='n';b<'r';b++)
				Key5.add(""+a+""+b);
		
		//List mk1
		ArrayList<Integer> mKey1 = new ArrayList<Integer>();
		for(int i=4;i<12;i++)
			mKey1.add(i);
		
		//List mk2
		LinkedList<String> mKey2 = new LinkedList<String>();
		for(int i=7;i<11;i++)
			for(char a='f';a<'m';a++)
				mKey2.add(Integer.toString(i)+""+a);

		//List m3
		ArrayList<Integer> mKey3 = new ArrayList<Integer>();
		for(int i=990;i<1010;i++)
			mKey3.add(i);
		
		//List mk4
		LinkedList<String> mKey4 = new LinkedList<String>();
		for(int i=26;i<29;i++)
			for(char a='v';a>'n';a--)
			{//System.out.println("List 4 ");
				mKey4.add(""+a+""+Integer.toString(i));
			}
//System.out.println("List 4:"+Key4);
		
		//List mk5
		
		LinkedList<String> mKey5 = new  LinkedList<String>();
		for(char a = 't';a<'w';a++)
			for(char b='o';b<'v';b++)
				mKey5.add(""+a+""+b);
		
		
		//mapkey1
		ArrayList<Integer> mapKey1 = new ArrayList<Integer>();
		Iterator<Integer> it = mKey1.iterator();
		while  (it.hasNext())
		{
		
			int  co=it.next();
			if(Key1.contains(co))
			{
				mapKey1.add(co);
			}
		}
		
		Iterator<Integer> iterate = mapKey1.iterator();
		while(iterate.hasNext())
		{
			int ob = iterate.next();
			if( ob %2==0)
			{
				
				iterate.remove();
			}
			 
			
		}
		 
		//List m_k2
		LinkedList<String> mapKey2 = new LinkedList<String>( );
		Iterator<String> it2 = mKey2.iterator();
		while  (it2.hasNext())
		{
		
			String  co2=it2.next();
			if(Key2.contains(co2))
			{
				mapKey2.add(co2);
			}
		}
		Iterator<String> iterate2 = mapKey2.iterator();
		boolean  flag = true;
		while(iterate2.hasNext())
		{
			iterate2.next();
			if( flag==false)
			{
				
				iterate.remove();
				flag =!flag;
			}
			 
			
		}
		//Iterators
		
		Iterator<Integer> iterateKey1 = Key1.iterator();
		Iterator<String> iterateKey2 = Key2.iterator();
		Iterator<Integer> iterateKey3 = Key3.iterator();
		Iterator<String> iterateKey4 = Key4.iterator();
		Iterator<String> iterateKey5 = Key5.iterator();
		 
		
		
		//print
		//System.out.println(mapKey1);
		//System.out.println(mapKey2);
		
		//System.out.println("returning..");
	

		//File
	File file = new File("D1.txt");
	if (!file.exists()) 
	{
		file.createNewFile();
	}
	
	FileWriter fr = null;
    try {
        fr = new FileWriter(file);
        int  ek1 ;
       
        //Iterator<Integer>  iterateK1 =  Key1.iterator();
        
        //Iterator<String> iterateK2 = K2.iterator();
        while(iterateKey1.hasNext())
        {
        	  ek1 =  iterateKey1.next() ;
        	   iterateKey2 = Key2.iterator();
        	  //System.out.println(" path1\n");
        	while(iterateKey2.hasNext())
        	{
        		//System.out.println(" path2");
        		String  ek2 =  iterateKey2.next();
        		iterateKey3= Key3.iterator();
        		while(iterateKey3.hasNext())
        		{
        			//System.out.println(" path3");
        			int ek3 = iterateKey3.next();
        			iterateKey4= Key4.iterator();
        			while(iterateKey4.hasNext())
        			{
        				//System.out.println(" path4");
        				String ek4 =  iterateKey4.next();
        				iterateKey5 = Key5.iterator();
        				while(iterateKey5.hasNext())
        				{
        					//System.out.println(" path5");
        					String  ek5 =  iterateKey5.next();
        					
        					fr.write("K1="+ ek1   +" & K2="+ek2+" & K3="+ek3+" & K4="+ek4+" & K5="+ek5+"\n");
        					//System.out.println("K1="+ ek1   +"&K2="+ek2+"&K3="+ek3+"&K4="+ek4+"&K5="+ek5+"\n");
        				}
        			}
        			
        		}
        		 
        		
        	}
        	
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }finally{
        //close resources
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    Iterator<Integer> iteratemKey1 = mKey1.iterator();
	Iterator<String> iteratemKey2 = mKey2.iterator();
	Iterator<Integer> iteratemKey3 = mKey3.iterator();
	Iterator<String> iteratemKey4 = mKey4.iterator();
	Iterator<String> iteratemKey5 = mKey5.iterator();
	
    File file2 = new File("D2.txt");

	// if file doesnt exists, then create it
	if (!file2.exists()) 
	{
		file2.createNewFile();
	}
    FileWriter fr2 = null;
    try {
        fr2 = new FileWriter(file2);
        int  ek1 ;
        
        //Iterator<Integer>  iterateK1 =  Key1.iterator();
        
        //Iterator<String> iterateK2 = K2.iterator();
        while(iteratemKey1.hasNext())
        {
        	  ek1 =  iteratemKey1.next() ;
        	   iteratemKey2 = mKey2.iterator();
        	  //System.out.println(" path1\n");
        	while(iteratemKey2.hasNext())
        	{
        		//System.out.println(" path2");
        		String  ek2 =  iteratemKey2.next();
        		iteratemKey3= mKey3.iterator();
        		while(iteratemKey3.hasNext())
        		{
        			//System.out.println(" path3");
        			int ek3 = iteratemKey3.next();
        			iteratemKey4= mKey4.iterator();
        			while(iteratemKey4.hasNext())
        			{
        				//System.out.println(" path4");
        				String ek4 =  iteratemKey4.next();
        				iteratemKey5 = mKey5.iterator();
        				while(iteratemKey5.hasNext())
        				{
        					//System.out.println(" path5");
        					String  ek5 =  iteratemKey5.next();
        					
        					fr2.write("K1="+ ek1   +" & K2="+ek2+" & K3="+ek3+" & K4="+ek4+" & K5="+ek5+"\n");
        					//System.out.println("K1="+ ek1   +"&K2="+ek2+"&K3="+ek3+"&K4="+ek4+"&K5="+ek5+"\n");
        				}
        			}
        			
        		}
        		 
        		
        	}
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }finally{
        //close resources
        try {
            fr2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	}
	
}
