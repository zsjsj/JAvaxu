package HightestTem;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Mykey implements WritableComparable<Mykey> {
    private int value;
    public int compareTo(Mykey o) {
        return o.getValue()-this.value;
    }

    public void write(DataOutput out) throws IOException {

    }

    public void readFields(DataInput in) throws IOException {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Mykey{" +
                "value=" + value +
                '}';
    }
}
