package net.bodz.bas.mf.std;

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
public abstract class AbstractCommonMdaFeatures<T>
        extends AbstractQueryable
        implements ICommonMdaFeatures<T>, IAttributes, ITextForm<T>, IValidator<T>, IClassifier<T>, ISearcher<T>,
        ISampleGenerator<T>, IInstanceStore<T> {

    protected final Class<T> type;
    private Attributes attributes;
    private Map<String, IAttributes> classicationAttributes;
    private Map<String, T> storeInstances;

    protected static final Random random = new Random();

    public AbstractCommonMdaFeatures(Class<T> type) {
        if (type == null)
            throw new NullPointerException("clazz");
        this.type = type;
    }

    /** @GeneratedBy CalcPreferredId.class */
    static final Map<Class<?>, Integer> commonMdaFeaturesIndex;
    static {
        commonMdaFeaturesIndex = new HashMap<Class<?>, Integer>();
        commonMdaFeaturesIndex.put(IAttributes.class, IAttributes.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(IClassifier.class, IClassifier.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(ICommonMdaFeatures.class, ICommonMdaFeatures.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(IFormatter.class, IFormatter.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(IInstanceStore.class, IInstanceStore.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(IParser.class, IParser.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(ISampleGenerator.class, ISampleGenerator.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(ISearcher.class, ISearcher.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(ITextForm.class, ITextForm.mdaFeatureIndex);
        commonMdaFeaturesIndex.put(IValidator.class, IValidator.mdaFeatureIndex);
    }

    @Override
    public <X> X query(Class<X> specificationType) {
        Integer index = commonMdaFeaturesIndex.get(specificationType);
        if (index == null)
            return super.query(specificationType);

        Object impl = query(index);

        if (impl == null)
            impl = __query(index);

        return specificationType.cast(impl);
    }

    /**
     * The indexed query is faster.
     */
    protected Object __query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case ICommonMdaFeatures.mdaFeatureIndex:
            return this;

        case IAttributes.mdaFeatureIndex:
            if (attributes != null)
                return this;
            break;

        case ITextForm.mdaFeatureIndex:
            Object parser = query(IParser.mdaFeatureIndex);
            Object formatter = query(IFormatter.mdaFeatureIndex);
            if (parser == formatter && parser != null)
                return parser;
            break;

        case IInstanceStore.mdaFeatureIndex:
            if (storeInstances != null)
                return this;
            break;
        }
        return null;
    }

    protected abstract Object _query(int mdaFeatureIndex);

    @Override
    public IAttributes getAttributes() {
        return this;
    }

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

    @Override
    public Object getAttribute(String attributeName) {
        return attributes.getAttribute(attributeName);
    }

    @Override
    public Collection<String> getAttributeNames() {
        return attributes.getAttributeNames();
    }

    @Override
    public ICommonMdaFeatures<?> getAttributeMdaFeatures(String attributeName) {
        return attributes.getAttributeMdaFeatures(attributeName);
    }

    @Override
    public String format(T object, IOptions options) {
        return format(object);
    }

    @Override
    public T parse(String text, IOptions options)
            throws ParseException {
        return parse(text);
    }

    @Override
    public void validate(T object, IOptions options)
            throws ValidationException {
        validate(object);
    }

    @Override
    public Map<String, Object> classify(T object, IOptions options)
            throws ClassifyException {
        return classify(object);
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

    @Override
    public Iterator<?> search(T object, String query, IOptions options) {
        return search(object, query);
    }

    @Override
    public T newSample(Map<String, Object> classification, IOptions options)
            throws CreateException {
        if (classification != null)
            if (!classification.isEmpty())
                return null;
        return newSample();
    }

    @Override
    public T getInstance(String name) {
        if (storeInstances == null)
            return null;
        return storeInstances.get(name);
    }

    @Override
    public Set<String> getInstanceNames() {
        if (storeInstances == null)
            return Collections.emptySet();
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

    protected final void addStaticFieldsToStore(Class<?> declaringClass) {
        if (declaringClass == null)
            throw new NullPointerException("declaringClass");
        for (Field field : declaringClass.getFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                try {
                    String name = field.getName();
                    Object object = field.get(null);
                    if (type.isInstance(object)) {
                        T instance = type.cast(object);
                        addStoreInstance(name, instance);
                    }
                } catch (IllegalAccessException e) {
                    throw new QueryException();
                }
            }
        }
    }

    // Default Implementatons

    @Override
    public T parse(String text)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public String format(T object) {
        return String.valueOf(object);
    }

    @Override
    public void validate(T object)
            throws ValidationException {
    }

    @Override
    public T newSample()
            throws CreateException {
        try {
            return type.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

    @Override
    public Iterator<?> search(T object, String query) {
        return Collections.emptyList().iterator();
    }

    @Override
    public Map<String, Object> classify(T object)
            throws ClassifyException {
        return Collections.emptyMap();
    }

}
