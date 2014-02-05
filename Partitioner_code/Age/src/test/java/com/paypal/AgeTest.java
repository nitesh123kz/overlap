package com.paypal;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.*;
import org.junit.Test;

import com.paypal.Age.*;

/**
 * Unit test for simple App.
 */
public class AgeTest
{
	  MapDriver<LongWritable, Text, Text, Text> mapDriver;
	  ReduceDriver<Text, Text, Text, IntWritable> reduceDriver;
	  MapReduceDriver<LongWritable, Text, Text, Text, Text, IntWritable> mapReduceDriver;
	  @Before
	  public void setUp() throws Exception {
	  
	  final MapClass mapper = new MapClass();
	  final Reduce reducer = new Reduce();
	  
	  mapDriver = MapDriver.newMapDriver(mapper);
	  reduceDriver = ReduceDriver.newReduceDriver(reducer);
	  mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	  }
	  @Test
	  public void testMapperWithSingleKeyAndValue() throws Exception {
	  /**
	   * input key and value
	   */  
	  final LongWritable inputKey = new LongWritable(0);
	  final Text inputValue = new Text("ram	19	male	89000");
	  /**
	   * output key and value
	   */
	  final Text outputKey = new Text("male");
	  final Text outputValue = new Text("ram	19	male	89000");
	  
	  mapDriver.withInput(inputKey, inputValue);
	  mapDriver.withOutput(outputKey, outputValue);
	  mapDriver.runTest();
	  
	  }
}
