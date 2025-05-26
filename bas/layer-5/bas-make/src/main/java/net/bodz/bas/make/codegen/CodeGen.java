package net.bodz.bas.make.codegen;

import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.fn.IExecutableVarArgsX;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CodeGen
        implements IExecutableVarArgsX<String, Exception> {

    static final Logger logger = LoggerFactory.getLogger(CodeGen.class);

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
                new IMakeSession__java(), //
        };

        for (int inputCount = 0; inputCount <= maxCount; inputCount++) {
            for (Class__java module : modules) {
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
        new CodeGen().execute(args);
    }

}
