package ReduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class MyMapper extends Mapper<LongWritable,Text,ComKey,NullWritable> {
    ComKey comKey;
    private int type;

    @Override
    protected void setup(Context context){
        try {
            FileSplit split = (FileSplit)context.getInputSplit();
            String name = split.getPath().getName();
            if (name.contains("cus")){
                type = 0;
            }else {
                type = 1;
            }
        }catch (Exception e){
            String message = e.getMessage();
            System.out.println(message);
        }

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        comKey = new ComKey();
        comKey.setType(type);
        comKey.setValue(line);
        context.write(comKey,NullWritable.get());
    }
}
