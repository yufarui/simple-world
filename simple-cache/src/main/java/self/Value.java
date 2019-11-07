package self;

import java.util.Date;

public class Value<V> {

    private V v;
    private Date date;

    public Value( V v) {
        this.v = v;
        this.date = new Date();
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Value{" +
                "v=" + v +
                ", date=" + date +
                '}';
    }
}
