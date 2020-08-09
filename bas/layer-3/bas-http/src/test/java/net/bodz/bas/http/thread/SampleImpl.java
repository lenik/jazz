package net.bodz.bas.http.thread;

public class SampleImpl
        implements ISampleApi {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public String cat(String a, String b) {
        return a + b;
    }

}
