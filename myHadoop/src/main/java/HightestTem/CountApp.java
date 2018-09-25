package HightestTem;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class CountApp {
    public static void main(String[] args) throws Exception {
        Configuration con = new Configuration();
        Job job = Job.getInstance(con);
        job.setJobName("count");
        job.setJarByClass(CountApp.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);

        job.setMapOutputKeyClass(CompKey.class);
        job.setMapOutputValueClass(NullWritable.class);
        FileInputFormat.setMaxInputSplitSize(job,25);
        job.setOutputKeyClass(Text.class);

        job.setOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(3);
        job.setPartitionerClass(MyHashPartition.class);
        //job.setPartitionerClass(MyPartation.class);
        job.setGroupingComparatorClass(MyGroupComparator.class);

        Path pathout = new Path("file:///e:\\ceshi\\out");
        FileSystem fs = FileSystem.get(con);
        if (fs.exists(pathout)){
            fs.delete(pathout,true);
        }
        FileInputFormat.addInputPath(job , new Path("file:///e:\\ceshi\\1.txt"));
        FileOutputFormat.setOutputPath(job , pathout);

        job.waitForCompletion(true);


    }
}
