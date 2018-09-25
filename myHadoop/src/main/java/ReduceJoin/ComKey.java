package ReduceJoin;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ComKey implements WritableComparable<ComKey> {
    private int type;
    private String value;

    public int compareTo(ComKey o) {
       if (this.type==o.type&&this.type==0){
           String[] strs = this.value.split(",");
           String[] ostr = o.value.split(",");
           return strs[0].compareTo(ostr[0]);
       }
       if (this.type==o.type&&this.type==1){
           String[] strs = this.value.split(",");
           String[] ostr = o.value.split(",");
           if (strs[strs.length-1].equals(ostr[ostr.length-1])){
               return strs[0].compareTo(ostr[0]);
           }else {
               return strs[strs.length-1].compareTo(ostr[ostr.length-1]);
           }
       }
       if (this.type==0&&o.type==1){
           String[] strs = this.value.split(",");
           String[] ostr = o.value.split(",");
           if (strs[0].equals(ostr[ostr.length-1])){
               return -1;
           }else {
               return strs[0].compareTo(ostr[ostr.length-1]);
           }
       }
        if (this.type==1&&o.type==0){
            String[] strs = this.value.split(",");
            String[] ostr = o.value.split(",");
            if (strs[0].equals(ostr[ostr.length-1])){
                return 1;
            }else {

                return strs[strs.length - 1].compareTo(ostr[0]);
            }
        }
        return 0;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(type);
        out.writeUTF(value);

    }

    public void readFields(DataInput in) throws IOException {
        this.type = in.readInt();
        this.value = in.readUTF();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ComKey{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
