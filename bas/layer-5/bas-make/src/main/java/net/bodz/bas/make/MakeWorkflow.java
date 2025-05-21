package net.bodz.bas.make;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.bodz.bas.make.sample.GlobNamePattern;
import net.bodz.bas.make.sample.GlobNames;
import net.bodz.bas.make.sample.NamedDouble;
import net.bodz.bas.make.sample.NamedFloat;
import net.bodz.bas.make.sample.NamedInteger;
import net.bodz.bas.make.sample.NamedList;
import net.bodz.bas.make.sample.NamedString;
import net.bodz.bas.make.util.FileEntry;
import net.bodz.bas.make.util.GlobPathPattern;
import net.bodz.bas.make.util.GlobPaths;
import net.bodz.bas.meta.decl.NotNull;

//interface ParameterMap<P,K> extends  Map

public class MakeWorkflow {

    Map<IKeyPattern<?, ?>, IParameterizedKeys<?, ?>> parameterMap;

    public static void main(String[] args)
            throws MakeException {
        GlobPathPattern param = new GlobPathPattern("foo%.o");

        FileEntry target = new FileEntry(Paths.get("foo-bar.o"));
        String wantStem = param.match(target.getKey());

        GlobPaths sourcePath = new GlobPaths("foo%.c");
        Path sourceKey = sourcePath.getKey(wantStem);

        FileEntry source = new FileEntry(sourceKey);
        System.out.println(source);

        MakeSession session = new MakeSession();

        NamedString name = new NamedString("name", "Lenik");
        NamedFloat age = new NamedFloat("age", 13.f);
        session.addData(name);
        session.addData(age);

        NamedString greet = new NamedString("greet");

        session.addRule(greet, name, age, (n, a) -> "hello " + n + ", you are " + a + " years now.");

        NamedInteger greetCount = new NamedInteger("greet_count");
        session.addRule(greetCount, greet, String::length);

        session.make(greetCount);

        NamedString brand = new NamedString("brand", "IBM");
        session.addData(brand);
        NamedInteger brandCount = new NamedInteger("brand_count");

        GlobNamePattern countPattern = new GlobNamePattern("%_count");
        GlobNames srcNames = new GlobNames("%");

        session.addPatternRule(countPattern, srcNames, //
                (CompileFunction1<NamedInteger, String, Integer, NamedString, String, String>) //
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