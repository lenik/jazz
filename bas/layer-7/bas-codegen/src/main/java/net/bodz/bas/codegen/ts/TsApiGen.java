package net.bodz.bas.codegen.ts;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.bean.api.IBeanDescriptor;
import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.codegen.IFileInfo;
import net.bodz.bas.codegen.MutableFileInfo;
import net.bodz.bas.codegen.SourceBuilder;
import net.bodz.bas.codegen.UpdateMethod;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.esm.EsmDomainMap;
import net.bodz.bas.esm.EsmImports;
import net.bodz.bas.esm.TypeScriptWriter;
import net.bodz.bas.esm.util.TsConfig;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.t.tuple.QualifiedName;

/**
 * TypeScript interface generator
 */
@ProgramName("tsapi")
public class TsApiGen
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(TsApiGen.class);

    /**
     * Specify the output dir.
     *
     * @option -O --outdir =DIR
     */
    Path baseDir;

    /**
     * Class mode, with empty implementation.
     *
     * @option -c
     */
    boolean classMode;

    UpdateMethod defaultUpdateMethod = UpdateMethod.DIFF_PATCH_UPGRADE;

    /**
     * Use .patch file to merge changes. Create new patches by compare the current file against the
     * generated file if no patch exist.
     *
     * @option -D
     * @see UpdateMethod#DIFF_PATCH_CREATE
     */
    public void createPatch() {
        defaultUpdateMethod = UpdateMethod.DIFF_PATCH_CREATE;
    }

    /**
     * Use .patch file to merge changes. Overwrite any existing content if no patch exist.
     *
     * @option -U
     * @see UpdateMethod#DIFF_PATCH_UPGRADE
     */
    public void upgrade() {
        defaultUpdateMethod = UpdateMethod.DIFF_PATCH_UPGRADE;
    }

    /**
     * Overwrite all existing files.
     *
     * @option -f
     */
    public void force() {
        defaultUpdateMethod = UpdateMethod.OVERWRITE;
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (baseDir == null) {
            logger.error("outdir isn't specified.");
            System.exit(1);
        }

        List<Class<?>> classes = new ArrayList<>();
        int nError = 0;

        for (String arg : args)
            try {
                Class<?> clazz = Class.forName(arg);
                classes.add(clazz);
            } catch (ClassNotFoundException e) {
                logger.error("invalid class name: " + arg);
                nError++;
            }

        if (nError != 0)
            System.exit(1);

        Builder builder = new Builder();
        int nFailed = 0;
        for (Class<?> clazz : classes)
            if (!builder.buildFile(clazz))
                nFailed++;

        if (nFailed != 0)
            logger.error(nFailed + " classes failed to be built.");
    }

    public static void main(String[] args)
            throws Exception {
        new TsApiGen().execute(args);
    }

    class Builder
            extends SourceBuilder<Class<?>> {

        @Override
        protected IFileInfo getFileInfo(Class<?> model) {
            String packagePath = model.getPackage().getName().replace('.', '/');
            String name = model.getSimpleName();
            return new MutableFileInfo(baseDir, packagePath, name + ".ts");
        }

        @Override
        public boolean build(ITreeOut out, Class<?> model) {
            QualifiedName qName = QualifiedName.of(model);
            EsmImports imports = EsmImports.forLocal(qName);
            EsmDomainMap domainMap = TsConfig.buildDomainMap(baseDir);

            BCharOut buf = new BCharOut();
            TypeScriptWriter tsw = new TypeScriptWriter(qName, buf.indented(), imports, domainMap);

            // BeanType type = BeanTypeProvider.getInstance().loadType(model);
            try {
                IBeanInfo beanInfo = Introspectors.getBeanInfo(model);
                build(tsw, beanInfo);
            } catch (IntrospectionException e) {
                logger.error(e, "Error get bean info from " + model);
                throw new RuntimeException(e);
            }

            int lines = tsw.im.dump(out, null);
            if (lines > 0)
                out.println();

            out.print(buf);
            out.flush();
            return true;
        }

//        boolean defaultOptional = true;

        boolean isOptional(AnnotatedElement el, boolean defaultOptional) {
            boolean required = el.isAnnotationPresent(NotNull.class);
            boolean optional = el.isAnnotationPresent(Nullable.class);
            if (required && optional)
                throw new IllegalUsageException("NotNull/Nullable only one can be present");
            if (required)
                return false;
            if (optional)
                return true;
            return defaultOptional;
        }

        boolean defaultOptional(Class<?> type) {
            if (type.isPrimitive())
                return false;
            return true;
        }

        public void build(TypeScriptWriter out, IBeanInfo beanInfo) {
            IBeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
            Class<?> beanClass = beanDescriptor.getBeanClass();

            out.enterln("export class " + beanDescriptor.getName() + " {");

            Field[] fields = beanClass.getFields();
            if (fields.length > 0)
                out.println();
            for (Field field : fields) {
                logger.debug("Field " + field.getName());
                makeField(out, field);
            }

            if (beanClass.getConstructors().length != 0)
                out.println();
            for (Constructor<?> ctor : beanClass.getConstructors()) {
                logger.debug("Constructor: " + MethodSignature.of(ctor));
                makeConstructor(out, ctor);
            }

            Set<Method> propertyMethods = new HashSet<>();
            for (IPropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                Method getter = property.getReadMethod();
                if (getter == null)
                    // throw new NullPointerException("getter of: " + property.getName());
                    continue;

                if (getter.getDeclaringClass() != beanClass)
                    continue;
                logger.debug("Property " + property.getName());
                makeProperty(out, property, propertyMethods);
            }

            for (Method method : beanClass.getDeclaredMethods()) {
                // Method mtd = method.getMethod();
                if (!Modifier.isPublic(method.getModifiers()))
                    continue;
                if (propertyMethods.contains(method))
                    continue;
//                if (mtd.getDeclaringClass() != beanDescriptor.getBeanClass())
//                    continue;
                logger.debug("Method " + MethodSignature.of(method));
                makeMethod(out, method, propertyMethods);
            }

            out.println();
            out.leaveln("}");
        }

        private void makeField(TypeScriptWriter out, Field field) {
            int modifiers = field.getModifiers();
            if (!Modifier.isPublic(modifiers))
                return;

            if (Modifier.isStatic(modifiers))
                out.print("static ");
            if (Modifier.isFinal(modifiers))
                out.print("readonly ");

            Type fieldType = field.getGenericType();
            String tsType = out.typeResolver.resolveGeneric(fieldType);

            out.print(field.getName());
            if (isOptional(field, defaultOptional(field.getType())))
                out.print("?");
            out.print(": ");

            out.print(tsType);
            out.println(";");
        }

        private void makeConstructor(TypeScriptWriter out, Constructor<?> ctor) {
            out.print("constructor");
            makeParameters(out, ctor.getParameters());
            out.println(";");
        }

        private void makeProperty(TypeScriptWriter out, IPropertyDescriptor property, Set<Method> propertyMethods) {
            Method getter = property.getReadMethod();
            String optMod = isOptional(getter, defaultOptional(property.getPropertyType())) ? " | undefined" : "";

            Type propertyType = property.getPropertyGenericType();
            String tsType = out.typeResolver.resolveGeneric(propertyType);
            out.println();
            if (property.getReadMethod() != null) {
                propertyMethods.add(property.getReadMethod());
                out.printf("get %s(): %s%s;\n", property.getName(), tsType, optMod);
            }
            if (property.getWriteMethod() != null) {
                propertyMethods.add(property.getWriteMethod());
                out.printf("set %s(val: %s%s);\n", property.getName(), tsType, optMod);
            }
        }

        private void makeMethod(TypeScriptWriter out, Method method, Set<Method> propertyMethods) {
            String tsReturnType = out.typeResolver.resolveGeneric(method.getGenericReturnType());

            out.println();

            if (Modifier.isStatic(method.getModifiers()))
                out.print("static ");

            out.print(method.getName());
            makeParameters(out, method.getParameters());
            out.printf(": %s;\n", tsReturnType);
        }

        private void makeParameters(TypeScriptWriter out, Parameter[] params) {
            List<String> tsParamTypes = new ArrayList<>();
            boolean[] optionals = new boolean[params.length];

            for (int i = 0; i < params.length; i++) {
                Parameter param = params[i];
                String tsParamType = out.typeResolver.resolveGeneric(param.getParameterizedType());
                optionals[i] = isOptional(param, defaultOptional(param.getType()));
                tsParamTypes.add(tsParamType);
            }

            out.print('(');
            for (int i = 0; i < tsParamTypes.size(); i++) {
                if (i != 0)
                    out.print(", ");
                String name = params[i].getName();
                out.print(name);
                if (optionals[i])
                    out.print("?");
                out.print(": ");
                out.print(tsParamTypes.get(i));
            }
            out.print(')');
        }

    }

}
