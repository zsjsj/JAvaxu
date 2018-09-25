package HightestTem;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartation extends Partitioner<IntWritable,IntWritable> {
    public int getPartition(IntWritable key, IntWritable value, int numPartitions) {
        Integer key1 = Integer.valueOf(key.toString());
        if (key1<1950){
            return 0;
        }else {
            if (key1<1985){
                return 1;
            }else {
                return 2;
            }
        }
    }
}
