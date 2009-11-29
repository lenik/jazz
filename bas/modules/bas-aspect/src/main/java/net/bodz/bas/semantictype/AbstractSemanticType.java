package net.bodz.bas.semantictype;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.api.annotations.ThreadUnsafe;
import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.exceptions.NoSuchKeyException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.Negotiation;
import net.bodz.bas.aspect.typeinfo.Formatter;
import net.bodz.bas.aspect.typeinfo.InstanceStore;
import net.bodz.bas.aspect.typeinfo.Parser;
import net.bodz.bas.aspect.typeinfo.SampleGenerator;
import net.bodz.bas.aspect.typeinfo.ValidateException;
import net.bodz.bas.aspect.typeinfo.Validator;
import net.bodz.bas.semantictype.annotation.TypeParameter;

@ThreadUnsafe
public abstract class AbstractSemanticType<T>
        implements ISemanticType<T>, Parser, Formatter, Validator, SampleGenerator<T> {

    private final String name;
    private final String description;
    private final String syntax;

    private final Class<? extends T> instanceClass;
    private final InstanceStore<T> instanceStore;

    private Parameter<?>[] parameters;
    private MeasureUnit measureUnit;

    public AbstractSemanticType(String name, String description, String syntax, Class<? extends T> instanceClass,
            InstanceStore<T> instanceStore) {
        if (name == null)
            throw new NullPointerException("name");
        if (instanceClass == null)
            throw new NullPointerException("instanceClass");
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.instanceClass = instanceClass;
        this.instanceStore = instanceStore;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Class<? extends T> getInstanceClass() {
        return instanceClass;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    @Override
    public Parser getParser() {
        return this;
    }

    @Override
    public Formatter getFormatter() {
        return this;
    }

    @Override
    public Validator getValidator() {
        return this;
    }

    @Override
    public SampleGenerator<T> getSampleGenerator() {
        return this;
    }

    @Override
    public Object parse(String text, Negotiation negotiation)
            throws ParseException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

    @Override
    public String format(Object object, Negotiation negotiation)
            throws NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return format(object);
    }

    @Override
    public void validate(Object object, Negotiation negotiation)
            throws ValidateException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        validate(object);
    }

    @Override
    public T newSample(Negotiation negotiation)
            throws CreateException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return newSample();
    }

    @Override
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    @Override
    public int getParameterCount() {
        introspectParameters();
        return parameters.length;
    }

    @Override
    public Parameter<?> getParameter(int parameterIndex) {
        introspectParameters();
        return parameters[parameterIndex];
    }

    @Override
    public Parameter<?> getParameter(String parameterName) {
        introspectParameters();
        if (parameterName == null)
            throw new NullPointerException("parameterName");
        for (int i = 0; i < parameters.length; i++)
            if (parameterName.equals(parameters[i].getName()))
                return parameters[i];
        throw new NoSuchKeyException(parameterName);
    }

    @Override
    public InstanceStore<T> getInstanceStore() {
        return instanceStore;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "semantic " + name;
    }

    private void introspectParameters() {
        Class<?> clazz = getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method readMethod = property.getReadMethod();
                if (readMethod == null)
                    continue;
                readMethod.getAnnotation(TypeParameter.class);
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
