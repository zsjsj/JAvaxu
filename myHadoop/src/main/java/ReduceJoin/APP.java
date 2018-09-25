package ReduceJoin;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class APP {
    public static void main(String[] args) throws Exception {
        Configuration con = new Configuration();
        Job job = Job.getInstance(con);
        job.setJobName("count");
        job.setJarByClass(APP.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);

        job.setMapOutputKeyClass(ComKey .class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        //job.setNumReduceTasks(3);
        job.setOutputValueClass(Text.class);
        job.setGroupingComparatorClass(MyGroupComparator.class);
        //job.setPartitionerClass(MyPartation.class);
        Path pathout = new Path("file:///e:\\ceshi\\out1");
        FileSystem fs = FileSystem.get(con);
        if (fs.exists(pathout)){
            fs.delete(pathout,true);
        }
        FileInputFormat.addInputPath(job , new Path("file:///e:\\ceshi\\in"));
        FileOutputFormat.setOutputPath(job , pathout);
        job.waitForCompletion(true);
    }
    }

