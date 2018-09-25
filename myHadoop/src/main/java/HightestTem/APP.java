package HightestTem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class APP {
    public static void main(String[] args) throws Exception {
        Configuration con = new Configuration();
        Job job = Job.getInstance(con);
        job.setJobName("count");
        job.setJarByClass(APP.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);

        job.setMapOutputKeyClass(IntWritable .class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        //job.setNumReduceTasks(3);
        job.setOutputValueClass(IntWritable.class);
        //job.setPartitionerClass(MyPartation.class);

        FileInputFormat.addInputPath(job , new Path("file:///e:\\ceshi\\out"));
        FileOutputFormat.setOutputPath(job , new Path("file:///e:\\ceshi\\out1"));

        boolean b = job.waitForCompletion(true);
    }
    }

