package total2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import total2.PassMapper;

public class PassApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("word count");

        //job入口函数类
        job.setJarByClass(PassApp.class);

        //设置mapper类
        job.setMapperClass(PassMapper.class);

        //设置reducer类
        job.setReducerClass(total2.PassReducer.class);


        //设置全排序采样类TotalOrderPartitioner
        job.setPartitionerClass(TotalOrderPartitioner.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //FileInputFormat.setMaxInputSplitSize(job,10);
        //FileInputFormat.setMinInputSplitSize(job,10);

        job.setInputFormatClass(KeyValueTextInputFormat.class);

        //设置输入路径
        FileInputFormat.addInputPath(job, new Path("D:/wc/out"));

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("D:/wc/out2"));

        //设置三个reduce
        job.setNumReduceTasks(3);


        /**
         * 随机采样，比较浪费性能，耗费资源
         * @param freq 每个key被选择的概率 ，大于采样数(2) / 所有key数量(n)
         * @param numSamples 所有切片中需要选择的key数量
         */
        //设置采样器类型
        InputSampler.RandomSampler<Text,Text> sampler = new InputSampler.RandomSampler<Text,Text>(0.01,10);

        //设置采样数据地址
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),new Path("D:/wc/par/"));

        //写入采样数据
        InputSampler.writePartitionFile(job,sampler);

        //执行job
        boolean b = job.waitForCompletion(true);

    }
}
