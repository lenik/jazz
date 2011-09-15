package net.bodz.bas.ant;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.collection.preorder.PrefixSet;
import net.bodz.bas.collection.preorder.SriTypeSet;
import net.bodz.bas.collection.set.IdentityHashSet;
import net.bodz.bas.context.clg.SystemCLG;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.log.api.Logger;
import net.bodz.bas.util.string.Strings;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Path;

public class PropertyBeanTask
        extends Task
        implements IPureTask {

    private String baseName;
    private ValueConstruct vCtor;

    private boolean smartExpand = false;

    private boolean expandArray = true;
    private boolean expandList = true;
    private boolean expandMap = true;

    private boolean expandProperties = false;
    private boolean expandFields = false;

    private PrefixSet stopTypePrefixes;
    static PrefixSet defaultStopTypePrefixes;
    static {
        defaultStopTypePrefixes = new PrefixSet();
        defaultStopTypePrefixes.add("java.");
        defaultStopTypePrefixes.add("javax.");
        defaultStopTypePrefixes.add("org.apache.tools.ant.");
    }

    private PrefixSet hiddenPrefixes;
    private int maxDepth = 16;
    private boolean unique = true;
    private String duplicatedMessage;

    private SriTypeSet refTypes;
    static SriTypeSet defaultRefTypes;
    static {
        defaultRefTypes = new SriTypeSet();
        // defaultRefTypes.add(ResourceCollection.class); // interface
        Class<?>[] types = { DataType.class, };
        for (Class<?> type : types)
            defaultRefTypes.add(type);
    }

    private boolean userProperties;

    private boolean verbose;
    private Logger logger = new TaskLogger(this);

    public PropertyBeanTask() {
        vCtor = new ValueConstruct();
        refTypes = new SriTypeSet();
        refTypes.addAll(defaultRefTypes);
        duplicatedMessage = "(duplicated node, terminated)";
    }

    public String getName() {
        return baseName;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("propertyName");
        this.baseName = name;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void addConfiguredStopTypePrefix(TextElement stopTypePrefix) {
        if (stopTypePrefixes == null)
            stopTypePrefixes = new PrefixSet();
        stopTypePrefixes.add(stopTypePrefix.getText());
    }

    public void hidePrefix(String prefix) {
        if (prefix == null)
            throw new NullPointerException("prefix");
        if (prefix.isEmpty())
            throw new IllegalArgumentException("empty prefix isn\'t allowed");
        if (hiddenPrefixes == null)
            hiddenPrefixes = new PrefixSet();
        hiddenPrefixes.add(prefix);
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getDuplicatedMessage() {
        return duplicatedMessage;
    }

    public void setDuplicatedMessage(String duplicatedMessage) {
        this.duplicatedMessage = duplicatedMessage;
    }

    public void setExpandProperties(boolean expandProperties) {
        this.expandProperties = expandProperties;
    }

    public void setExpandFields(boolean expandFields) {
        this.expandFields = expandFields;
    }

    public void setExpandArray(boolean expandArray) {
        this.expandArray = expandArray;
    }

    public void setExpandList(boolean expandList) {
        this.expandList = expandList;
    }

    public void setExpandMap(boolean expandMap) {
        this.expandMap = expandMap;
    }

    public void addConfiguredRefType(TextElement refTypeText)
            throws ClassNotFoundException {
        String typeName = refTypeText.getText();
        Class<?> type = Jdk7Reflect.forName(typeName);
        refTypes.add(type);
    }

    /** overwrite user variables */
    public boolean isUser() {
        return userProperties;
    }

    public void setUser(boolean user) {
        this.userProperties = user;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void addConfiguredLogFile(LoggerVC logger) {
        // logger=new ChainedLogTerm();
        throw new NotImplementedException();
    }

    String join(String parent, String child) {
        boolean hide = false;
        if (hiddenPrefixes != null) {
            String hiddenPrefix = hiddenPrefixes.floor(child);
            hide = hiddenPrefix != null;
        }
        if (hide)
            return parent;
        else
            return parent + "." + child;
    }

    String nullText = "null";

    void setProperty(String name, String value) {
        Project project = getProject();
        if (value == null)
            value = nullText;
        if (userProperties)
            project.setUserProperty(name, value);
        else
            project.setNewProperty(name, value);
    }

    @Override
    public void execute()
            throws BuildException {
        if (baseName == null)
            throw new IllegalUsageException("Property name isn\'t specified");

        final Project project = getProject();
        File baseDir = project.getBaseDir();
        if (baseDir != null)
            SystemCLG.cwd.chdir(baseDir);

        try {
            Class<?>[] tryProjectTypes = { Project.class };
            Object[] tryProjectValues = { project };
            Object instance;
            try {
                instance = vCtor.create(tryProjectTypes, tryProjectValues);
            } catch (CreateException e) {
                instance = vCtor.create();
            }
            Traverser traverser = new Traverser(project);
            traverser.traverse(baseName, instance, 0);
        } catch (CreateException e) {
            logger.error(e);
            throw new BuildException(e);
        } catch (Exception e) {
            logger.error(e);
            throw new BuildException("Traverse Error", e);
        }
    }

    class Traverser {

        Project project;
        IdentityHashSet uniqSet;
        PrefixSet stp;

        public Traverser(Project project) {
            this.project = project;
            if (unique)
                this.uniqSet = new IdentityHashSet();
            if ((stp = stopTypePrefixes) == null)
                stp = defaultStopTypePrefixes;
        }

        public void traverse(String _name, Object node, int level)
                throws Exception {
            if (verbose) {
                String abbr = Strings.ellipse(String.valueOf(node), 30);
                logger.debug(Strings.repeat(level, ' ') + _name + " = " + abbr);
            }

            if (node == null) {
                setProperty(_name, null);
                return;
            }

            Class<? extends Object> clazz = node.getClass();
            Class<?> refType = refTypes.floor(clazz);
            if (refType != null) {
                project.addReference(_name, node);
                return;
            }

            boolean canExpand = true;
            if (!_name.isEmpty()) {
                String s;
                if (unique) {
                    if (uniqSet.contains(node)) {
                        s = duplicatedMessage;
                        canExpand = false;
                    } else {
                        s = String.valueOf(node);
                        uniqSet.add(node);
                    }
                } else
                    s = String.valueOf(node);
                setProperty(_name, s);
            }
            if (!canExpand)
                return;
            if (level >= maxDepth)
                return;

            X: do {
                if (expandArray && clazz.isArray()) {
                    int len = Array.getLength(node);
                    for (int i = 0; i < len; i++) {
                        String name = String.valueOf(i);
                        Object value = Array.get(node, i);
                        traverse(join(_name, name), value, level + 1);
                    }
                    break X;
                }

                if (expandList && node instanceof Collection<?>) {
                    Collection<?> collection = (Collection<?>) node;
                    int i = 0;
                    for (Object value : collection) {
                        String name = String.valueOf(i++);
                        traverse(join(_name, name), value, level + 1);
                    }
                    if (smartExpand)
                        break X;
                }

                if (expandMap) {
                    if (node instanceof Map<?, ?>) {
                        Map<?, ?> map = (Map<?, ?>) node;
                        for (Entry<?, ?> e : map.entrySet()) {
                            String name = String.valueOf(e.getKey());
                            Object value = e.getValue();
                            traverse(join(_name, name), value, level + 1);
                        }
                        if (smartExpand)
                            break X;
                    }
                    if (node instanceof Dictionary<?, ?>) {
                        Dictionary<?, ?> dict = (Dictionary<?, ?>) node;
                        Enumeration<?> keys = dict.keys();
                        while (keys.hasMoreElements()) {
                            Object key = keys.nextElement();
                            Object value = dict.get(key);
                            String name = String.valueOf(key);
                            traverse(join(_name, name), value, level + 1);
                        }
                        if (smartExpand)
                            break X;
                    }
                }

                String stopTypePrefix = stp.floor(clazz.getName());
                boolean stopped = stopTypePrefix != null;
                if (stopped)
                    break;

                if (expandProperties) {
                    // XXX: Java 7? getBeanInfo(class, stop-class, 0)
                    BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
                    for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                        Method readf = property.getReadMethod();
                        if (readf == null)
                            continue;
                        int argc = readf.getParameterTypes().length;
                        if (argc != 0)
                            continue;
                        String name = property.getName();
                        Object value;
                        try {
                            value = readf.invoke(node);
                        } catch (Exception e) {
                            value = "Can\'t read bean property " + name + ": " + e;
                        }
                        traverse(join(_name, name), value, level + 1);
                    }
                    // break X;
                }

                if (expandFields) {
                    Field[] fields = clazz.getFields();
                    for (Field field : fields) {
                        String name = field.getName();
                        // for static field, node is ignored
                        Object value = field.get(node);
                        traverse(join(_name, name), value, level + 1);
                    }
                    // break X;
                }
            } while (false);
        } // traverse()

    } // Traverser

    // delegates

    public void addConfiguredParameter(Parameter param)
            throws ParseException {
        vCtor.addConfiguredParameter(param);
    }

    public void setClassName(String className) {
        vCtor.setClassName(0, className); // XXX ?
    }

    public void setObject(Object obj) {
        vCtor.setObject(obj);
    }

    public void setXml(String xml) {
        vCtor.setXml(xml);
    }

    public void setXmlFile(File xmlFile) {
        vCtor.setXmlFile(xmlFile);
    }

    public void addConfiguredClasspath(Path path) {
        vCtor.addConfiguredClasspath(path);
    }

}
