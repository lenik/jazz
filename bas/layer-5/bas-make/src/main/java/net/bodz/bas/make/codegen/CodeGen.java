package net.bodz.bas.make.codegen;

import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.fn.IExecutableVarArgsX;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;

public class CodeGen
        implements IExecutableVarArgsX<String, Exception> {

    int maxCount = 7;

    @Override
    public void execute(String... args)
            throws Exception {
        MavenPomDir pomDir = MavenPomDir.fromClass(getClass());
        if (pomDir == null)
            throw new NullPointerException("pomDir");

        Path baseDir = pomDir.getBaseDir();
        Path javaDir = baseDir.resolve("src/main/java");

        Class__java[] modules = { //
                new CompileFunction__java(), //
                new IMakeable__java(), //

                new IMakeRule__java(), //
                new SimpleMakeRule__java(), //

                new IKeyPatternLikeMakeRule__java(), //
                new SimpleKeyPatternLikeMakeRule__java(), //

                new IKeyPatternMakeRule__java(), //
                new SimpleKeyPatternMakeRule__java(), //
                new IDataTypedKeyPatternMakeRule__java(), //
                new SimpleDataTypedKeyPatternMakeRule__java(), //

                new ITargetTypedKeyPatternMakeRule__java(), //
                new SimpleTargetTypedKeyPatternMakeRule__java(), //

                new ITargetPatternLikeMakeRule__java(), //
                new SimpleTargetPatternLikeMakeRule__java(), //

                new ITargetPatternMakeRule__java(), //
                new SimpleTargetPatternMakeRule__java(), //

                new SimpleMakeRuleBuilders__java(), //
                new SimpleKeyPatternMakeRuleBuilders__java(), //
                new SimpleDataTypedKeyPatternMakeRuleBuilders__java(), //
                new SimpleTargetTypedKeyPatternMakeRuleBuilders__java(), //

                new SimpleTargetPatternMakeRuleBuilders__java(), //

                new IMakeRules__java(), //
        };

        for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
            for (Class__java module : modules) {
                if (module.isSingle() && inputCount != maxCount)
                    continue;

                module.setInputCount(inputCount);
                module.setMaxCount(maxCount);

                BCharOut buf = new BCharOut();
                ITreeOut out = buf.indented();

                JavaCodeWriter javaWriter = new JavaCodeWriter(module.getQName().packageName, out);

                module.build(javaWriter);

                String code = buf.toString();

                Path file = module.getQName().toPath(javaDir, "java");
                System.out.println("write to " + file);
                Files.write(file, code.getBytes());
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        try {
            new CodeGen().execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
