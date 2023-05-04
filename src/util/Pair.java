package util;

public class Pair<T, S> {
    private T p0;
    private S p1;

    public Pair() {
    }

    public Pair(T p0, S p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    public T getP0() {
        return p0;
    }

    public void setP0(T p0) {
        this.p0 = p0;
    }

    public S getP1() {
        return p1;
    }

    public void setP1(S p1) {
        this.p1 = p1;
    }
}
