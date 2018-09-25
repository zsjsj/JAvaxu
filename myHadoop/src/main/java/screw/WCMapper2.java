package com.oldboy.mr.screw;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Random;

public class WCMapper2 extends Mapper<LongWritable, Text, Text, IntWritable> {


    /**
     * map函数，被调用过程是通过while循环每行调用一次
     * 重新设计key
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //将value变为string格式
        String line = value.toString();

        //切割一行文本为k-v
        String[] arr = line.split("\t");

        String word = arr[0];
        Integer count = Integer.parseInt(arr[1]);

        //将一行文本进行截串

        String newWord = word.split("_")[0];
        context.write(new Text(newWord), new IntWritable(count));

    }
}
