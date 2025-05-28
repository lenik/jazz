package net.bodz.bas.codegen.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.util.regex.TextPreps;

public class Digits2FmtSpec
        extends TextPreps.Matches {

    List<Object> args = new ArrayList<>();

    public Digits2FmtSpec() {
        super("(\\d+\\.\\d*)|(\\d+)");
    }

    @Override
    protected void matched(CharSequence in, int start, int end, Appendable out)
            throws IOException {
        if (matcher.group(1) != null) {
            double d = Double.parseDouble(matcher.group(1));
            args.add(d);
            out.append("%f");
        } else if (matcher.group(2) != null) {
            int num = Integer.parseInt(matcher.group(2));
            args.add(num);
            out.append("%d");
        }
    }

    public static void main(String[] args) {
        String s = "foo1, bar2.3.";
        Digits2FmtSpec nums = new Digits2FmtSpec();
        String t = nums.process(s);
        System.out.println(t);
        System.out.println(nums.args);
    }

}