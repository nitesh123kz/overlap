package com.paypal.mpi;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CampaignMetricStage2Driver   extends Configured implements Tool {
  private static final String JOB_PERIOD = "JOB_PERIOD";

  @SuppressWarnings("static-access")
	protected static final Option OPTION_INPUT_PATHS = OptionBuilder.withArgName("path").hasArg().isRequired(false).withDescription("Input paths (comma-separated)").create("input");
	@SuppressWarnings("static-access")
	protected static final Option OPTION_OUTPUT_PATH = OptionBuilder.withArgName("path").hasArg().isRequired(false).withDescription("Output path").create("outputBasePath");
	@SuppressWarnings("static-access")
	protected static final Option OPTION_PERIOD = OptionBuilder.withArgName("yyyy/mm/dd/hh24").hasArg().isRequired(false).withDescription("Period for which this job is run in yyyy/mm/dd/hh24").create("period");
  @SuppressWarnings("static-access")
  protected static final Option OPTION_FOLDER = OptionBuilder.withArgName("path").hasArg().isRequired(false).withDescription("Subfolder inside the  period like stage/session ").create("InputFolder");


  private CommandLine parseCommandLine(String[] args) throws ParseException {
    Options options = new Options();

    options.addOption(OPTION_INPUT_PATHS);
    options.addOption(OPTION_OUTPUT_PATH);
    options.addOption(OPTION_PERIOD);
    options.addOption(OPTION_FOLDER);
    
    CommandLineParser parser = new PosixParser();
    CommandLine cmdLine = null;
    HelpFormatter help = new HelpFormatter();

    try {
      cmdLine = parser.parse(options, args);
    } catch (ParseException e) {
      help.printHelp(this.getClass().getSimpleName(), options);
      System.exit(-1);
    }
    return cmdLine;
  }

 
  public int run(String[] args) throws IOException,InterruptedException, ClassNotFoundException, ParseException {
	  
	 CommandLine cmdLine = parseCommandLine(args);
		Configuration conf = this.getConf();
		conf.set("period",cmdLine.getOptionValue("period"));
		System.out.println("entering stage-2");
		DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver","jdbc:mysql://10.65.255.114:3306/CampaignStudio", "ciuser", "ci@paypal");
		// Job Details
		@SuppressWarnings( "deprecation" )
		Job job = new Job(conf, "CampaignMetric-Stage2");
		job.setJarByClass(CampaignMetricStage2Driver.class);
		job.setMapperClass(Mapper.class);
		job.setReducerClass(CopyOfCampaignMetricReducer.class);
		
		job.setMapOutputKeyClass(CampaignKey.class);
		job.setMapOutputValueClass(CampaignEvent.class);
		
		//job.setOutputKeyClass(Text.class);
		job.setOutputKeyClass(CampaignEventCountDBWritable.class);
		job.setOutputValueClass(NullWritable.class);
		
		/*
		  String folder_path=cmdLine.getOptionValue("InputFolder");
		if(folder_path.length()<2)
		{
			folder_path = "/sessions/completed/";
		}
		String inputPath = cmdLine.getOptionValue("input") + 
				CampaignMetricConstants.FW_SLASH + cmdLine.getOptionValue("period")+"/"+folder_path;
		FileInputFormat.addInputPaths(job, inputPath);
		job.setInputFormatClass(SequenceFileInputFormat.class);
		 */
		String inputPath = cmdLine.getOptionValue("outputBasePath") + 
				CampaignMetricConstants.FW_SLASH + cmdLine.getOptionValue("period")+"/";
		job.setInputFormatClass(SequenceFileInputFormat.class);
		System.out.println("input:"+inputPath);
		
		job.setOutputFormatClass(DBOutputFormat.class);
		FileInputFormat.addInputPath(job, new Path(inputPath));
		/*String outputPath = cmdLine.getOptionValue("outputBasePath") + 
				CampaignMetricConstants.FW_SLASH + cmdLine.getOptionValue("period")+"/"+"stage-2/";
		FileOutputFormat.setOutputPath(job, new Path(outputPath));*/
		
		DBOutputFormat.setOutput(job,"CampaignMetricGenericCounts", "CampaignID", "OfferID","OfferVID","timestamp","Browser","Device","OS","Location","IM_COUNT","CL_COUNT","CV_COUNT",  "Count");
		//DBOutputFormat.setOutput(job,"CampaignMetricEventCounts", "CampaignID", "OfferID","OfferVID","timestamp","IM_COUNT","CL_COUNT","CV_COUNT");
				/******
				 * 
				 */
		// Set Output format and path
				//LazyOutputFormat.setOutputFormatClass(job, SequenceFileOutputFormat.class);
				String outputPath = cmdLine.getOptionValue("outputBasePath") + 
						CampaignMetricConstants.FW_SLASH + cmdLine.getOptionValue("period")+"/stage-2";
				
				FileOutputFormat.setOutputPath(job, new Path(outputPath));
				
				// Setup other outputs as needed.
				MultipleOutputs.addNamedOutput(job, "DBOutput", DBOutputFormat.class, CampaignEventCountDBWritable.class,   NullWritable.class);
				MultipleOutputs.addNamedOutput(job, "completedstage2", SequenceFileOutputFormat.class, CampaignKey.class, CampaignMetric.class);
				/*****************
				 * 
				 
		MultipleOutputs.addNamedOutput(job, "DBOutput", DBOutputFormat.class, CampaignMetricGenericCountDBWritable.class,   NullWritable.class);
		MultipleOutputs.addNamedOutput(job, "completed", SequenceFileOutputFormat.class, Text.class, Text.class);*/
		
		return job.waitForCompletion(true) ? 0 : 1;
  }


  public static void main(String[] args) throws Exception {
	  System.out.println("job-2");
    ToolRunner.run(new Configuration(), new CampaignMetricStage2Driver(), args);
  }
}
