package net.bodz.bas.typer.std;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ThreadUnsafe;
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.QueryException;

@ThreadUnsafe
public abstract class AbstractCommonTypers<T>
        extends AbstractQueryable
        implements IBasicTyperFamily<T>, ITextForm<T>, IValidator<T>, IClassifier<T>, ISearcher<T>,
        ISampleGenerator<T>, IInstanceStore<T> {

    protected final Class<T> type;
    private Map<String, IAttributes> classicationAttributes;
    private Map<String, T> storeInstances;

    protected static final Random random = new Random();

    public AbstractCommonTypers(Class<T> type) {
        if (type == null)
            throw new NullPointerException("clazz");
        this.type = type;
    }

    /** @GeneratedBy CalcPreferredId.class.getName() */
    static final Map<String, Integer> commonTyperIndex;
    static {
        /**
         * TODO scan indexed typers and generate the index.
         */
        commonTyperIndex = new HashMap<String, Integer>();
        commonTyperIndex.put(IClassifier.class.getName(), IClassifier.typerIndex);
        commonTyperIndex.put(IBasicTyperFamily.class.getName(), IBasicTyperFamily.typerIndex);
        commonTyperIndex.put(IFormatter.class.getName(), IFormatter.typerIndex);
        commonTyperIndex.put(IInstanceStore.class.getName(), IInstanceStore.typerIndex);
        commonTyperIndex.put(IParser.class.getName(), IParser.typerIndex);
        commonTyperIndex.put(ISampleGenerator.class.getName(), ISampleGenerator.typerIndex);
        commonTyperIndex.put(ISearcher.class.getName(), ISearcher.typerIndex);
        commonTyperIndex.put(ITextForm.class.getName(), ITextForm.typerIndex);
        commonTyperIndex.put(IValidator.class.getName(), IValidator.typerIndex);
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType) {
        String specification = specificationType.getName();
        Integer index = commonTyperIndex.get(specification);
        if (index == null)
            return super.query(specificationType);

        Object impl = queryInt(index);

        if (impl == null)
            impl = queryIntDefaultImpl(index);

        return specificationType.cast(impl);
    }

    /**
     * The indexed query is faster.
     */
    protected Object queryIntDefaultImpl(int typerIndex) {
        switch (typerIndex) {
        case IBasicTyperFamily.typerIndex:
            return this;

        case ITextForm.typerIndex:
            Object parser = queryInt(IParser.typerIndex);
            Object formatter = queryInt(IFormatter.typerIndex);
            if (parser == formatter && parser != null)
                return parser;
            break;

        case IInstanceStore.typerIndex:
            if (storeInstances != null)
                return this;
            break;
        }
        return null;
    }

    protected abstract Object queryInt(int typerIndex);

    /** ⇱ Implementation Of {@link IBasicTyperFamily}. */
    /* _____________________________ */static section.iface __BASIC__;

    @Override
    public IClassifier<T> getClassifier() {
        return this;
    }

    @Override
    public IFormatter<T> getFormatter() {
        return this;
    }

    @Override
    public IInstanceStore<T> getInstanceStore() {
        return this;
    }

    @Override
    public IParser<T> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<T> getSampleGenerator() {
        return this;
    }

    @Override
    public ISearcher<T> getSearcher() {
        return this;
    }

    @Override
    public ITextForm<T> getTextForm() {
        return this;
    }

    @Override
    public IValidator<T> getValidator() {
        return this;
    }

    /** ⇱ Implementation Of {@link IFormatter}. */
    /* _____________________________ */static section.iface __FORMAT__;

    @Override
    public String format(T object) {
        return format(object, IOptions.NULL);
    }

    @Override
    public String format(T object, IOptions options) {
        return String.valueOf(object);
    }

    /** ⇱ Implementation Of {@link IParser}. */
    /* _____________________________ */static section.iface __PARSE__;

    @Override
    public T parse(String text)
            throws ParseException {
        return parse(text, IOptions.NULL);
    }

    @Override
    public T parse(String text, IOptions options)
            throws ParseException {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IValidator}. */
    /* _____________________________ */static section.iface __VALIDATE__;

    @Override
    public final void validate(T object)
            throws ValidationException {
        validate(object, IOptions.NULL);
    }

    @Override
    public void validate(T object, IOptions options)
            throws ValidationException {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IClassifier}. */
    /* _____________________________ */static section.iface __CLASSIFY__;

    @Override
    public final Map<String, Object> classify(T object)
            throws ClassifyException {
        return classify(object, IOptions.NULL);
    }

    @Override
    public Map<String, Object> classify(T object, IOptions options)
            throws ClassifyException {
        return Collections.emptyMap();
    }

    @Override
    public IAttributes getClassAnnotations(String classId) {
        if (classicationAttributes == null)
            return null;
        IAttributes annotations = classicationAttributes.get(classId);
        return annotations;
    }

    protected final void addClassAnnotation(String classId, IAttributes annotations) {
        if (classicationAttributes == null)
            classicationAttributes = new TreeMap<String, IAttributes>();
        classicationAttributes.put(classId, annotations);
    }

    /** ⇱ Implementation Of {@link ISearcher}. */
    /* _____________________________ */static section.iface __SEARCH__;

    @Override
    public final Iterator<?> search(T object, String query) {
        return search(object, query, IOptions.NULL);
    }

    @Override
    public Iterator<?> search(T object, String query, IOptions options) {
        return Collections.emptyList().iterator();
    }

    /** ⇱ Implementation Of {@link ISampleGenerator}. */
    /* _____________________________ */static section.iface __SAMPLE__;

    @Override
    public final T newSample()
            throws CreateException {
        return newSample(IOptions.NULL);
    }

    @Override
    public T newSample(IOptions options)
            throws CreateException {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IInstanceStore}. */
    /* _____________________________ */static section.iface __STORE__;

    @Override
    public T getInstance(String name) {
        if (storeInstances == null)
            return null;
        else
            return storeInstances.get(name);
    }

    @Override
    public Set<String> getInstanceNames() {
        if (storeInstances == null)
            return Collections.emptySet();
        else
            return Collections.unmodifiableSet(storeInstances.keySet());
    }

    static final String autoNameFormat = "(%d)";
    private int nextInstanceIndex = 0;

    protected final void addStoreInstance(T instance) {
        if (instance == null)
            throw new NullPointerException("instance");
        String name = String.format(autoNameFormat, nextInstanceIndex++);
        addStoreInstance(name, instance);
    }

    protected final void addStoreInstance(String name, T instance) {
        if (name == null)
            throw new NullPointerException("name");
        if (instance == null)
            throw new NullPointerException("instance");
        if (storeInstances == null)
            storeInstances = new TreeMap<String, T>();
        storeInstances.put(name, instance);
    }

    protected final void addStoreInstancesFromStaticFields(Class<?> declaringClass, Class<? extends T> restriction) {
        if (declaringClass == null)
            throw new NullPointerException("declaringClass");
        for (Field field : declaringClass.getFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                try {
                    String name = field.getName();
                    Object value = field.get(null);
                    if (restriction.isInstance(value)) {
                        T instance = restriction.cast(value);
                        addStoreInstance(name, instance);
                    }
                } catch (IllegalAccessException e) {
                    throw new QueryException();
                }
            }
        }
    }

}
