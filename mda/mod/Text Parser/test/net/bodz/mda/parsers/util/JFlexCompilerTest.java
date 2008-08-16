package net.bodz.mda.parsers.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.err.CompileException;
import net.bodz.bas.types.util.CompatMethods;
import net.bodz.mda.loader.AutoCompileLoader;
import net.bodz.mda.loader.JavaCompiler;
import net.bodz.mda.parsers.JFlexLexer;
import net.bodz.mda.parsers.LexMatch;
import net.bodz.mda.parsers.Token;

import org.junit.Test;

public class JFlexCompilerTest {

    @Test
    public void testCompileOnly() throws CompileException {
        JFlexCompiler compiler = new JFlexCompiler();
        // compiler.clean = false;
        compiler.f_classSimpleName = "Cat";
        String[] rv = compiler.compile(null, null, ". { return 1; }\n");
        String lexImpl = rv[JFlexCompiler.R_IMPL];
        // System.out.println(lexImpl);
        assertTrue(lexImpl != null);
    }

    @SuppressWarnings("unchecked")
    void compileRun(String lexClassName, String lexImpl) throws Exception {
        // how to let javac's classloader include the current jvm.
        JavaCompiler javac = new JavaCompiler();
        AutoCompileLoader loader = new AutoCompileLoader(javac);
        System.out.println("Compile...");
        javac.inheritsClasspath();
        javac.compile(lexImpl, lexClassName);
        System.out.println("Compiled");
        Class<JFlexLexer> lexClass = (Class<JFlexLexer>) loader
                .loadClass(lexClassName);
        System.out.println("Class=" + lexClass);

        String text = "hello\nworld!\n";
        Reader textIn = new StringReader(text);
        JFlexLexer lexer = CompatMethods.newInstance(lexClass, textIn);
        System.out.println("Inst=" + lexer);
        Token token;
        int i = 0;
        while ((token = lexer.read()) != null) {
            int id = token.getId();
            assertEquals("token[" + i + "]", text.charAt(i), (char) id);
            System.out.println(token);
            i++;
        }
        assertEquals("lex len", text.length(), i);
        lexer.close();
    }

    @Test
    public void testCompileRunSimple() throws Exception {
        JFlexCompiler jflexc = new JFlexCompiler();
        // jflexc.clean = false;

        String packageName = getClass().getPackage().getName();
        jflexc.f_classSimpleName = "TestLex";
        jflexc.f_classExtends = JFlexLexer.class.getSimpleName();
        String className = packageName + "." + jflexc.f_classSimpleName;

        Buffer header = new Buffer();
        Buffer decl = new Buffer();
        Buffer def = new Buffer();

        header.println("package " + packageName + "; ");
        header.println("import " + JFlexLexer.class.getCanonicalName() + "; ");
        header.println("import java.io.*; ");

        decl.println("%{");
        decl.println("  public " + jflexc.f_classSimpleName + "(Reader in) {");
        decl.println("      super(in); ");
        decl.println("  }");
        decl.println("%}");

        def.println(".|\\n { return yycharat(0); }");

        String[] rv = jflexc.compile(header.toString(), decl.toString(), def
                .toString());
        String lexImpl = rv[JFlexCompiler.R_IMPL];
        assertTrue(lexImpl != null);

        compileRun(className, lexImpl);
    }

    public static class MyLexer extends JFlexLexer {

        @LexMatch(value = ".|\\n")
        static final int ANYCHAR = 0;

        public MyLexer(Reader in) {
            super(in);
        }

        public MyLexer(URL url) throws IOException {
            super(Files.getReader(url));
        }

        @LexMatch(value = "{ANYCHAR}")
        protected int anychar(String s) {
            return s.charAt(0);
        }

    }

    @Test
    public void testCompileRunModel() throws Exception {
        JFlexCompiler jflexc = new JFlexCompiler();
        jflexc.clean = false;

        String className = JFlexCompiler.getImplClassName(MyLexer.class);
        String[] rv = jflexc.compile(MyLexer.class);

        String lexImpl = rv[JFlexCompiler.R_IMPL];
        assertTrue(lexImpl != null);

        compileRun(className, lexImpl);
    }

}
