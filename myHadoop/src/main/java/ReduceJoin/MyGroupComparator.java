package ReduceJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class MyGroupComparator extends WritableComparator {


    protected MyGroupComparator() {
        super(ComKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ComKey ck1 = (ComKey) a;
        ComKey ck2 = (ComKey) b;
        int type1 = ck1.getType();
        int type2 = ck2.getType();
        if (type1==type2&&type1==0){
            String value = ck1.getValue();
            String[] strs = value.split(",");
            String value2 = ck2.getValue();
            String[] strs2 = value2.split(",");
            return strs[0].compareTo(strs2[0]);
        }
        if (type1==0&&type2==1){
            String value = ck1.getValue();
            String[] strs = value.split(",");
            String value2 = ck2.getValue();
            String[] strs2 = value2.split(",");
            return strs[0].compareTo(strs2[strs2.length-1]);
        }
        if (type1==1&&type2==0){
            String value = ck1.getValue();
            String[] strs = value.split(",");
            String value2 = ck2.getValue();
            String[] strs2 = value2.split(",");
            return strs[strs.length-1].compareTo(strs2[0]);
        }
        if (type1==1&&type2==1){
            String value = ck1.getValue();
            String[] strs = value.split(",");
            String value2 = ck2.getValue();
            String[] strs2 = value2.split(",");
            return strs[strs.length-1].compareTo(strs2[strs2.length-1]);
        }
       return 0;
    }
}
