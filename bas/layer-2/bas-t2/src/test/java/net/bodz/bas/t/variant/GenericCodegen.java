package net.bodz.bas.t.variant;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.err.LoadException;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

public abstract class GenericCodegen {

    protected static final String K_PRIMITIVE = "primitive";
    protected static final String K_DEFAULT = "default";
    protected static final String K_DATE = "date";
    protected static final String K_OTHER = "other";

    protected Map<String, List<TypeInfo>> typeGroups = new LinkedHashMap<>();

    protected List<TypeInfo> primitiveTypes = new ArrayList<>();
    protected List<TypeInfo> defaultTypes = new ArrayList<>();
    protected List<TypeInfo> dateTypes = new ArrayList<>();
    protected List<TypeInfo> otherTypes = new ArrayList<>();

    List<JavaMethod> methods;
    Map<String, List<String>> templates;

    public GenericCodegen() {
        primitiveTypes = conv( //
                byte.class, //
                short.class, //
                int.class, //
                long.class, //
                float.class, //
                double.class, //
                boolean.class, //
                char.class, //
                null);

        defaultTypes = conv( //
                BigInteger.class, //
                BigDecimal.class, //
                File.class, //
                URI.class, //
                URL.class, //
                null);

        dateTypes = conv(//
                Date.class, //
                Instant.class, //
                ZonedDateTime.class, //
                OffsetDateTime.class, //
                LocalDateTime.class, //
                LocalDate.class, //
                LocalTime.class, //
                null);

        otherTypes = conv( //
                Class.class, //
                File.class, //
                // Path.class, //
                String.class, //
                null);

        typeGroups.put(K_PRIMITIVE, primitiveTypes);
        typeGroups.put(K_DEFAULT, defaultTypes);
        typeGroups.put(K_DATE, dateTypes);
        typeGroups.put(K_OTHER, otherTypes);
    }

    List<TypeInfo> conv(Class<?>... types) {
        List<TypeInfo> list = new ArrayList<>(types.length);
        for (Class<?> type : types) {
            if (type == null)
                continue;
            TypeInfo info = new TypeInfo(type);
            list.add(info);
        }
        return list;
    }

    public static class TypeInfo {
        public Class<?> type;
        public Class<?> boxedType;

        public TypeInfo(Class<?> type) {
            this.type = type;
            this.boxedType = Primitives.box(type);
        }
    }

    Path findSourceFile() {
        Class<?> clazz = getClass();
        MavenPomDir pomDir = MavenPomDir.fromClass(clazz);
        Path sourceFile = pomDir.getSourceFile(clazz);
        return sourceFile;
    }

    protected synchronized Map<String, List<String>> getTemplates() {
        if (templates == null)
            try {
                templates = loadTemplatesFromSource();
            } catch (IOException e) {
                throw new LoadException(e.getMessage(), e);
            }
        return templates;
    }

    static final String _BEGIN_TEMPLATE = "\\beginTemplate";
    static final String _END_TEMPLATE = "\\endTemplate";

    Map<String, List<String>> loadTemplatesFromSource()
            throws IOException {
        Path sourceFile = findSourceFile();
        List<String> lines = Files.readAllLines(sourceFile);

        Map<String, List<String>> templates = new LinkedHashMap<>();
        String templateName = null;
        List<String> buf = new ArrayList<>();

        for (String line : lines) {
            if (templateName == null) {
                int pos = line.indexOf(_BEGIN_TEMPLATE);
                if (pos != -1) {
                    templateName = line.substring(pos + _BEGIN_TEMPLATE.length()).trim();
                    continue;
                }
            } else {
                int pos = line.indexOf(_END_TEMPLATE);
                if (pos != -1) {
                    ArrayList<String> copy = new ArrayList<String>(buf);
                    buf.clear();
                    templates.put(templateName, copy);
                    templateName = null;
                    continue;
                }
                buf.add(line);
            }
        }
        return templates;
    }

    protected synchronized List<JavaMethod> getMethodsToGenerate() {
        if (methods == null)
            try {
                loadMethodsToGenerateFromSource();
            } catch (IOException e) {
                throw new LoadException(e.getMessage(), e);
            }
        return methods;
    }

    void loadMethodsToGenerateFromSource()
            throws IOException {
        JavaProjectBuilder project = new JavaProjectBuilder();
        Path sourceFile = findSourceFile();
        project.addSource(sourceFile.toFile());

        Class<?> clazz = getClass();
        JavaClass javaClass = project.getClassByName(clazz.getName());
        List<JavaMethod> list = new ArrayList<>();
        for (JavaMethod method : javaClass.getMethods()) {
            boolean generated = false;
            Iterator<JavaAnnotation> iterator = method.getAnnotations().iterator();
            while (iterator.hasNext()) {
                JavaAnnotation annotation = iterator.next();
                String name = annotation.getType().getFullyQualifiedName();
                if (name.equals(Generated.class.getName())) {
                    generated = true;
                    iterator.remove();
                    break;
                }
            }
            if (!generated)
                continue;

            list.add(method);
        }
        methods = list;
    }

    protected final void execute(String[] args)
            throws Exception {
        mainImpl(args);
    }

    protected abstract void mainImpl(String[] args)
            throws Exception;

}
