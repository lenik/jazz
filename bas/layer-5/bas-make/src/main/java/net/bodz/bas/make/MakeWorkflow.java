package net.bodz.bas.make;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.make.util.FileBin;
import net.bodz.bas.make.util.GlobPathPattern;
import net.bodz.bas.make.util.GlobPaths;
import net.bodz.bas.make.util.GlobStringNames;
import net.bodz.bas.make.util.GlobStringPatternForInt;
import net.bodz.bas.make.util.NamedDouble;
import net.bodz.bas.make.util.NamedFloat;
import net.bodz.bas.make.util.NamedInteger;
import net.bodz.bas.make.util.NamedList;
import net.bodz.bas.make.util.NamedString;

//interface ParameterMap<P,K> extends  Map

public class MakeWorkflow {

    public static void main(String[] args)
            throws MakeException {
        GlobPathPattern param = new GlobPathPattern("foo%.o");

        FileBin target = FileBin.builder().path(Paths.get("foo-bar.o")).build();
        String wantStem = param.match(target.getKey());

        GlobPaths sourcePath = new GlobPaths("foo%.c");
        Path sourceKey = sourcePath.getKey(wantStem);

        FileBin source = FileBin.builder().path(sourceKey).build();
        System.out.println(source);

        MakeSession session = new MakeSession();

        NamedString name = new NamedString("name", "Lenik");
        NamedFloat age = new NamedFloat("age", 13.f);
        session.addData(name);
        session.addData(age);

        NamedString greet = new NamedString("greet");

//        session.addRule(greet, name, age, (n, a) -> "hello " + n + ", you are " + a + " years now.");

        NamedInteger greetCount = new NamedInteger("greet_count");
        session.addRule(greetCount, greet, String::length);

        session.make(greetCount);

        NamedString brand = new NamedString("brand", "IBM");
        session.addData(brand);
        NamedInteger brandCount = new NamedInteger("brand_count");

        GlobStringPatternForInt countPattern = new GlobStringPatternForInt("%_count");
//        GlobNames srcNames = new GlobNames("%");
        GlobStringNames srcNames = new GlobStringNames("%");

        session.addPatternRule(countPattern, srcNames, //
                (count, input1) -> String::length);

        System.out.println("greet: " + greet);
        System.out.println("count: " + greetCount.getData());

        System.out.println("brand: " + brand);
        session.make(brandCount);
        System.out.println("count: " + brandCount);

        List<String> colorList = new ArrayList<>(Arrays.asList("red", "black", "blue"));
        NamedList<String> colors = (NamedList<String>) new NamedList<>("color", colorList);

        NamedInteger colorCount = new NamedInteger("colorCount");
        session.addRule(colorCount, colors, List::size);
        session.make(colorCount);
        System.out.println("List count: " + colorCount);

        List<Double> numList = new ArrayList<>(Arrays.asList(10.0, 20.0, 30.0));
        NamedList<Double> nums = new NamedList<>("nums", numList);
        NamedDouble sum = new NamedDouble("sum");

        session.addRule(sum, nums, list -> list.stream().mapToDouble(a -> a).sum());
        session.make(sum);
        System.out.println("Sum: " + sum);
    }

}