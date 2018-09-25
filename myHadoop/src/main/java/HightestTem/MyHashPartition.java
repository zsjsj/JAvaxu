package HightestTem;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import secondarysort.CompKey;


public class MyHashPartition extends Partitioner<CompKey,NullWritable> {
    @Override
    public int getPartition(CompKey compKey, NullWritable nullWritable, int numPartitions) {
        String year = compKey.getYear();
        Integer key1 = Integer.valueOf(year);

        if (key1 < 1950) {
            return 0;
        } else {
            if (key1 < 1985) {
                return 1;
            } else {
                return 2;
            }

        }
    }
}
