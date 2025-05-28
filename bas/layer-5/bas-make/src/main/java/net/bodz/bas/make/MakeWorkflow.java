package net.bodz.bas.make;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.object.ClassLiterals;
import net.bodz.bas.make.util.FileBin;
import net.bodz.bas.make.util.GlobPathPattern;
import net.bodz.bas.make.util.GlobPaths;
import net.bodz.bas.make.util.KeyModifier_;
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

        MakeRules rules = new MakeRules();
        MakeSession session = new MakeSession(rules);

        NamedString name = new NamedString("name", "Lenik");
        NamedFloat age = new NamedFloat("age", 13.f);
        session.addData(name);
        session.addData(age);

        NamedString greet = new NamedString("greet");
        rules.addRule(greet, name, age, (n, a) -> "hello " + n + ", you are " + a + " years now.");

        NamedInteger greetCount = new NamedInteger("greet_count");
        rules.addRule(greetCount, greet, String::length);

        session.make(greetCount);
        System.out.println("greetCount: " + greetCount);

        GlobStringPatternForInt countPattern = new GlobStringPatternForInt("%_count");
        KeyModifier_<String> justStem_Str = new KeyModifier_<>("%", String.class);

        rules.addPatternRule(countPattern, justStem_Str, //
                (count, input1) -> String::length);

        NamedString brand = new NamedString("brand", "IBM And Microsoft");
        session.addData(brand);

        NamedInteger brandCount = new NamedInteger("brand_count");
        session.make(brandCount);
        System.out.println("brandCount: " + brandCount);

        List<String> colorList = new ArrayList<>(Arrays.asList("red", "black", "blue"));
        NamedList<String> colors = (NamedList<String>) new NamedList<>("color", colorList);
        session.addData(colors);

        //rules.addRule(colorCount, colors, List::size);
//        GlobNames srcNames = new GlobNames("%");
        KeyModifier_<List<?>> justStem_List = new KeyModifier_<>("%", ClassLiterals.List_class);
        rules.addPatternRule(countPattern, justStem_List, //
                (count, input1) -> List::size);

        NamedInteger colorCount = new NamedInteger("color_count");
        session.make(colorCount);
        System.out.println("List count: " + colorCount);

        List<Double> numList = new ArrayList<>(Arrays.asList(10.0, 20.0, 30.0));
        NamedList<Double> nums = new NamedList<>("nums", numList);
        NamedDouble sum = new NamedDouble("sum");

        rules.addRule(sum, nums, list -> list.stream().mapToDouble(a -> a).sum());
        session.make(sum);
        System.out.println("Sum: " + sum);
    }

}