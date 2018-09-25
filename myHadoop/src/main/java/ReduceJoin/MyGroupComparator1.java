package ReduceJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class MyGroupComparator1 extends WritableComparator {


    protected MyGroupComparator1() {
        super(ComKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
       return 0;
    }
}
