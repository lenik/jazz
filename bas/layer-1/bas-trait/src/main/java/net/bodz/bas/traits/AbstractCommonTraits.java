package net.bodz.bas.traits;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.mi.AbstractQueryable;
import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.meta.codehint.ThreadUnsafe;

@ThreadUnsafe
public abstract class AbstractCommonTraits<T>
        extends AbstractQueryable
        implements ICommonTraits<T>, IAttributes, ITextForm<T>, IValidator<T>, IClassifier<T>, ISearcher<T>,
        ISampleGenerator<T>, IInstanceStore<T> {

    protected final Class<T> type;
    private Attributes attributes;
    private Map<String, IAttributes> classicationAttributes;
    private Map<String, T> storeInstances;

    protected static final Random random = new Random();

    public AbstractCommonTraits(Class<T> type) {
        if (type == null)
            throw new NullPointerException("clazz");
        this.type = type;
    }

    /** @GeneratedBy CalcPreferredId.class */
    static final Map<Class<?>, Integer> commonTraitsIndex;
    static {
        commonTraitsIndex = new HashMap<Class<?>, Integer>();
        commonTraitsIndex.put(IAttributes.class, IAttributes.traitIndex);
        commonTraitsIndex.put(IClassifier.class, IClassifier.traitIndex);
        commonTraitsIndex.put(ICommonTraits.class, ICommonTraits.traitIndex);
        commonTraitsIndex.put(IFormatter.class, IFormatter.traitIndex);
        commonTraitsIndex.put(IInstanceStore.class, IInstanceStore.traitIndex);
        commonTraitsIndex.put(IParser.class, IParser.traitIndex);
        commonTraitsIndex.put(ISampleGenerator.class, ISampleGenerator.traitIndex);
        commonTraitsIndex.put(ISearcher.class, ISearcher.traitIndex);
        commonTraitsIndex.put(ITextForm.class, ITextForm.traitIndex);
        commonTraitsIndex.put(IValidator.class, IValidator.traitIndex);
    }

    @Override
    public <X> X query(Class<X> specificationType) {
        Integer traitIndex = commonTraitsIndex.get(specificationType);
        if (traitIndex == null)
            return super.query(specificationType);
        Object traits = queryIndexed(traitIndex);
        return specificationType.cast(traits);
    }

    /**
     * The indexed query is faster.
     */
    protected Object queryIndexed(int traitIndex) {
        Object traits = query(traitIndex);
        if (traits != null)
            return traits;

        switch (traitIndex) {
        case ICommonTraits.traitIndex:
            return this;

        case IAttributes.traitIndex:
            if (attributes != null)
                return this;
            break;

        case ITextForm.traitIndex:
            Object parser = query(IParser.traitIndex);
            Object formatter = query(IFormatter.traitIndex);
            if (parser == formatter && parser != null)
                return parser;
            break;

        case IInstanceStore.traitIndex:
            if (storeInstances != null)
                return this;
            break;
        }
        return null;
    }

    protected abstract Object query(int traitIndex);

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
    public ICommonTraits<?> getAttributeTraits(String attributeName) {
        return attributes.getAttributeTraits(attributeName);
    }

    @Override
    public String format(T object, INegotiation negotiation)
            throws NegotiationException {
        if (negotiation != null)
            negotiation.ignore();
        return format(object);
    }

    @Override
    public T parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        if (negotiation != null)
            negotiation.ignore();
        return parse(text);
    }

    @Override
    public void validate(T object, INegotiation negotiation)
            throws ValidateException, NegotiationException {
        if (negotiation != null)
            negotiation.ignore();
        validate(object);
    }

    @Override
    public Map<String, Object> classify(T object, INegotiation negotiation)
            throws ClassifyException, NegotiationException {
        if (negotiation != null)
            negotiation.ignore();
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
    public Iterator<?> search(T object, String query, INegotiation negotiation)
            throws NegotiationException {
        if (negotiation != null)
            negotiation.ignore();
        return search(object, query);
    }

    @Override
    public T newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException {
        if (classification != null)
            if (!classification.isEmpty())
                return null;
        if (negotiation != null)
            negotiation.ignore();
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
            throws ValidateException {
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
