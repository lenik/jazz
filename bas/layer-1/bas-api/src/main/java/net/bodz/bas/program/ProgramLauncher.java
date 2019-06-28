package net.bodz.bas.program;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.meta.build.ProgramName;

@ProgramName("progexec")
public class ProgramLauncher {

    public static void main(String[] args)
            throws Exception {
        if (args.length < 1)
            throw new IllegalArgumentException("Expected program classname.");
        String prog = args[0];

        Class<?> mainClass = null;
        Method mainMethod;
        try {
            mainClass = Class.forName(prog);
        } catch (ClassNotFoundException e) {
            // continue to find.
        }
        if (mainClass == null)
            mainClass = resolveProgram(prog);

        if (mainClass == null)
            throw new IllegalArgumentException("Program is unknown: " + prog);

        try {
            mainMethod = mainClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            throw new Error(e.getMessage(), e);
        }

        args = Arrays.copyOfRange(args, 0, args.length - 1);
        mainMethod.invoke(null, (Object) args);
    }

    static Pattern programLine = Pattern.compile("^(\\S+)\\s*=\\s*(\\w+)\\s+(\\S+)\\s*$");

    static Class<?> resolveProgram(String prog)
            throws IOException {
        // find in META-INF/programs.
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();

        Enumeration<URL> resources = loader.getResources("META-INF/programs");
        while (resources.hasMoreElements()) {
            URL programsFile = resources.nextElement();
            InputStream in = programsFile.openStream();
            ByteArrayOutputStream buf = new ByteArrayOutputStream(1000);
            byte[] block = new byte[4096];
            int nb;
            while ((nb = in.read(block)) != -1)
                buf.write(block, 0, nb);
            in.close();

            String contents = new String(buf.toByteArray(), "utf-8");
            BufferedReader rdr = new BufferedReader(new StringReader(contents));
            String line;
            while ((line = rdr.readLine()) != null) {
                Matcher matcher = programLine.matcher(line);
                if (matcher.find()) {
                    String alias = matcher.group(1);
                    String type = matcher.group(2);
                    String fqcn = matcher.group(3);
                    switch (type) {
                    case "main":
                        if (alias.equals(prog))
                            try {
                                return Class.forName(fqcn);
                            } catch (ClassNotFoundException e) {
                                throw new Error(String.format("No class %s for program %s.", fqcn, prog), e);
                            }
                        break;
                    }
                }
            }
        }
        return null;
    }

}
