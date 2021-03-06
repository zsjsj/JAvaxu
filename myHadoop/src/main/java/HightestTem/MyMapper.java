package HightestTem;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

public class MyMapper extends Mapper<LongWritable ,Text,CompKey,NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] arr = value.toString().split(" ");
        String year = arr[0];
        int temp = Integer.parseInt(arr[1]);

        CompKey ck = new CompKey(year,temp);

        context.write(ck,NullWritable.get());
    }
}

