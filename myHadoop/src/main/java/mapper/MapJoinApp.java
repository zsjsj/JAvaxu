package mapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MapJoinApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS","file:///");

        conf.set("customer.path","D:/wc/customers.txt");

        Job job = Job.getInstance(conf);

        FileSystem fs = FileSystem.get(conf);


        //设置job名称
        job.setJobName("Map join");

        //job入口函数类
        job.setJarByClass(MapJoinApp.class);

        //设置mapper类
        job.setMapperClass(MapJoinMapper.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        Path pin = new Path("D:/wc/orders.txt");
        Path pout = new Path("D:/wc/out");

        //job.setInputFormatClass(KeyValueTextInputFormat.class);

        //设置输入路径
        FileInputFormat.addInputPath(job, pin);


        //设置输出路径
        FileOutputFormat.setOutputPath(job,pout );

        if (fs.exists(pout)) {
            fs.delete(pout,true);
        }

        job.setNumReduceTasks(1);

        //执行job
        job.waitForCompletion(true);


    }
}
