package ReduceJoin;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReduce extends Reducer<ComKey,NullWritable,Text,Text> {
    @Override
    protected void reduce(ComKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            String line = null;
        for(NullWritable value: values){
            int type = key.getType();
            if (type==0){
                String value1 = key.getValue();
                line = value1;
            }else {
                String value1 = key.getValue();
                if (line==null){
                    context.write(new Text("null"),new Text(value1));
                }else {
                    context.write(new Text(line),new Text(value1));
                }

            }
        }
    }
}
