package net.bodz.lily.tool.javagen;

import java.io.File;
import java.util.Random;

import net.bodz.bas.codegen.ClassPathInfo;
import net.bodz.bas.codegen.UpdateMethod;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class JavaGenProject {

    static final Logger logger = LoggerFactory.getLogger(JavaGenProject.class);

    File baseDir;
    String daoPackage = "dao";
    UpdateMethod updateMethod;

    Random random;

    public final ClassPathInfo Foo;
    public final ClassPathInfo _Foo_stuff;
    public final ClassPathInfo IFoo_Id;
    public final ClassPathInfo Foo_Id;
    public final ClassPathInfo Foo_IdAccessor;

    public final ClassPathInfo FooTest; // test
    public final ClassPathInfo _Foo_stuffTest; // test
    public final ClassPathInfo Foo_IdTest; // test
    public final ClassPathInfo Foo_IdAccessorTest; // test

    public final ClassPathInfo _FooMask_stuff;
    public final ClassPathInfo FooMask;
    public final ClassPathInfo FooMaskTest; // test

    public final ClassPathInfo FooMapper;
    public final ClassPathInfo FooIndex;

    public final ClassPathInfo FooSamples; // test
    public final ClassPathInfo FooMapperTest; // test
    public final ClassPathInfo FooIndexTest; // test

    public JavaGenProject(File baseDir, ClassPathInfo Foo, Random random) {
        this.baseDir = baseDir;
        this.Foo = Foo;
        this.random = random;

        String dao_ = daoPackage + ".";
        String generated = "src/main/generated";

        _Foo_stuff = Foo.join("_" + Foo.name + "_stuff", generated);
        IFoo_Id = Foo.join("I" + Foo.name + "_Id");
        Foo_Id = Foo.join(Foo.name + "_Id");
        Foo_IdAccessor = Foo.join(Foo.name + ".Id");

        FooTest = Foo.join(Foo.name + "Test", "src/test/java", "src/test/resources");
        _Foo_stuffTest = FooTest.join("_" + Foo.name + "_stuffTest");
        Foo_IdTest = FooTest.join(Foo.name + "_IdTest");
        Foo_IdAccessorTest = FooTest.join(Foo.name + "_IdAccessorTest");

        _FooMask_stuff = Foo.join(dao_ + "_" + Foo.name + "Mask_stuff", generated);
        FooMask = Foo.join(dao_ + Foo.name + "Mask");
        FooMaskTest = FooTest.join(dao_ + Foo.name + "MaskTest");

        FooMapper = Foo.join(dao_ + Foo.name + "Mapper");
        FooIndex = Foo.join(dao_ + Foo.name + "Index");

        FooSamples = FooTest.join(Foo.name + "Samples", generated);
        FooMapperTest = FooTest.join(dao_ + Foo.name + "MapperTest");
        FooIndexTest = FooTest.join(dao_ + Foo.name + "IndexTest");
    }

    public UpdateMethod getPreferredUpdateMethod() {
        return updateMethod;
    }

    public void setPreferredUpdateMethod(UpdateMethod updateMethod) {
        if (updateMethod == null)
            throw new NullPointerException("updateMethod");
        this.updateMethod = updateMethod;
    }

}
