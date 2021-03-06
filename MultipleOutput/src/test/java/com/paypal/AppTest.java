package com.paypal;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.*;
import org.apache.hadoop.mrunit.mapreduce.*;
import org.apache.hadoop.mrunit.types.Pair;
import org.apache.hadoop.io.*;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit test for simple App.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({MultipleOutputs.class, WordCountReducer.class})
public class AppTest 
    extends TestCase
{
	  MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	  ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	  MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	 
	  @Before
	  public void setUp() {
	    WordCountMapper mapper = new WordCountMapper();
	    WordCountReducer reducer = new WordCountReducer();
	    mapDriver = MapDriver.newMapDriver(mapper);
	    reduceDriver = ReduceDriver.newReduceDriver(reducer);
	    mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	  }
	  
	  @org.junit.Test
	  public void test1()   throws    Exception  
	  {
		  List<IntWritable> list = new  LinkedList<IntWritable>();
		  list.add(new IntWritable(1));
		  list.add(new IntWritable(1));
	  reduceDriver.withInput(new Text("first"),list  );
	  List<Pair<Text,Text>>  pair = new  LinkedList<Pair<Text, Text>>();
	  pair.add(new Pair<Text,Text>(new Text("first"), new Text("first0")));
	  pair.add(new Pair<Text,Text>(new Text("first"), new Text("first1")));
	  
      reduceDriver.withMultiOutput("output1", new Text("first"), new IntWritable(2));
      reduceDriver.withMultiOutput("output2", pair.get(0));
      reduceDriver.withMultiOutput("output2", pair.get(1));
      reduceDriver.runTest();
	  }
}
