package net.bodz.mda.parsers.util;

import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.StringReader;

import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.err.CompileException;
import net.bodz.bas.types.util.CompatMethods;
import net.bodz.mda.loader.AutoCompileLoader;
import net.bodz.mda.loader.JavaCompiler;
import net.bodz.mda.parsers.JFlexLexer;
import net.bodz.mda.parsers.Token;

import org.junit.Test;

public class JFlexCompilerTest {

    @Test
    public void test1() throws CompileException {
        JFlexCompiler compiler = new JFlexCompiler();
        // compiler.clean = false;
        compiler.f_classSimpleName = "Cat";
        String[] rv = compiler.compile(null, null, ". { return 1; }\n");
        String lexImpl = rv[JFlexCompiler.R_IMPL];
        // System.out.println(lexImpl);
        assertTrue(lexImpl != null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test2() throws Exception {
        JFlexCompiler jflexc = new JFlexCompiler();
        jflexc.clean = false;

        String packageName = getClass().getPackage().getName();
        jflexc.f_classSimpleName = "TestLex";
        jflexc.f_classExtends = JFlexLexer.class.getCanonicalName();
        String className = packageName + "." + jflexc.f_classSimpleName;

        Buffer header = new Buffer();
        Buffer decl = new Buffer();
        Buffer def = new Buffer();

        header.println("package " + packageName + "; ");
        header.println("import " + JFlexLexer.class.getCanonicalName() + "; ");

        // decl.println("%{");
        // decl.println("  public " + jflexc.f_classSimpleName + "() {");
        // decl.println("      super(null); ");
        // decl.println("  }");
        // decl.println("%}");

        def.println(". { return 1; }");

        String[] rv = jflexc.compile(header.toString(), decl.toString(), def
                .toString());
        String lexImpl = rv[JFlexCompiler.R_IMPL];
        assertTrue(lexImpl != null);

        // how to let javac's classloader include the current jvm.
        JavaCompiler javac = new JavaCompiler();
        AutoCompileLoader loader = new AutoCompileLoader(javac);
        System.out.println("Compile...");
        javac.inheritsClasspath();
        javac.compile(lexImpl, className);
        System.out.println("Compiled");
        Class<JFlexLexer> lexClass = (Class<JFlexLexer>) loader
                .loadClass(className);
        System.out.println("Class=" + lexClass);

        String text = "hello";
        Reader textIn = new StringReader(text);
        JFlexLexer lexer = CompatMethods.newInstance(lexClass, textIn);
        System.out.println("Inst=" + lexer);
        Token token;
        while ((token = lexer.read()) != null) {
            System.out.println(token);
        }
        lexer.close();
    }

}
