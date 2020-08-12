package net.bodz.bas.potato.provider.bean;

import com.googlecode.openbeans.BeanDescriptor;
import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.EventSetDescriptor;
import com.googlecode.openbeans.MethodDescriptor;
import com.googlecode.openbeans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.*;
import net.bodz.bas.potato.provider.reflect.ReflectModifiers;
import net.bodz.mda.xjdoc.IXjdocProvider;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class BeanType
        extends AbstractType {

    private BeanDescriptor beanDescriptor;
    private Class<?> beanClass;

    private MutablePropertyMap propertyMap = new MutablePropertyMap(false);
    private MutableMethodMap methodMap = new MutableMethodMap(false);
    private IConstructorMap constructorMap = NullConstructorMap.getInstance();
    private MutableEventMap eventMap = new MutableEventMap(false);

    private final int modifiers;
    private final int detailLevel;
    private final int priority;

    public BeanType(BeanInfo beanInfo, int infoset, ClassDoc classDoc, IXjdocProvider docLoader) {
        super(beanInfo.getBeanDescriptor().getBeanClass(), //
                beanInfo.getBeanDescriptor().getName(), //
                classDoc);

        boolean declaredOnly = (infoset & BeanTypeProvider.I_DeclaredOnly) != 0;

        beanDescriptor = beanInfo.getBeanDescriptor();
        beanClass = beanDescriptor.getBeanClass();

        int _modifiers = beanClass.getModifiers();
        this.modifiers = _modifiers;

        DetailLevel aDetailLevel = beanClass.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.detailLevel = aDetailLevel.value();
        else
            this.detailLevel = ReflectModifiers.toDetailLevel(_modifiers);

        Priority aPriority = beanClass.getAnnotation(Priority.class);
        priority = aPriority == null ? 0 : aPriority.value();

        boolean docs = (infoset & ITypeProvider.I_Docs) != 0;

        if ((infoset & ITypeProvider.I_Properties) != 0) {
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method getter = propertyDescriptor.getReadMethod();
                Method setter = propertyDescriptor.getWriteMethod();
                if (getter == null && setter == null)
                    // TODO This could be an indexed property. Not supported, yet.
                    continue;
                if (getter == null && (infoset & BeanTypeProvider.I_WriteOnly) == 0)
                    continue;
                if (setter == null && (infoset & BeanTypeProvider.I_ReadOnly) == 0)
                    continue;

                if (declaredOnly) {
                    if (getter != null && getter.getDeclaringClass() != beanClass)
                        continue;
                    if (setter != null && setter.getDeclaringClass() != beanClass)
                        continue;
                }

                String name = propertyDescriptor.getName();
                MethodDoc propertyDoc = null;
                if (docs) {
                    propertyDoc = docLoader.getMethodDoc(beanClass, classDoc, getter, setter);
                    if (propertyDoc == null) {
                        propertyDoc = MethodDoc.n_a(classDoc, new MethodId(getter != null ? getter : setter));
                        propertyDoc.setTag(IElementDoc.LABEL, iString.fn.val(Strings.hyphenatize(name)));
                    }
                }

                BeanProperty beanProperty = new BeanProperty(beanClass, propertyDescriptor, propertyDoc);
                propertyMap.addProperty(beanProperty);
            }
        }

        if ((infoset & ITypeProvider.I_Methods) != 0) {
            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
            for (MethodDescriptor methodDescriptor : methodDescriptors) {
                Method method = methodDescriptor.getMethod();
                if (declaredOnly)
                    if (method.getDeclaringClass() != beanClass)
                        continue;

                MethodDoc methodDoc = null;
                if (docs) {
                    methodDoc = docLoader.getMethodDoc(beanClass, classDoc, method);
                    if (methodDoc == null)
                        methodDoc = MethodDoc.n_a(classDoc, new MethodId(method));
                }

                BeanMethod beanMethod = new BeanMethod(methodDescriptor, methodDoc);
                methodMap.addMethod(beanMethod);
            }
        }

        if ((infoset & ITypeProvider.I_Events) != 0) {
            EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
            for (EventSetDescriptor eventSetDescriptor : eventSetDescriptors) {
                Method addListener = eventSetDescriptor.getAddListenerMethod();
                if (declaredOnly)
                    if (addListener != null && addListener.getDeclaringClass() != beanClass)
                        continue;

                IElementDoc eventDoc = null;
                if (docs) {
                    // TODO Event xjdoc..
                }

                BeanEvent beanEvent = new BeanEvent(beanClass, eventSetDescriptor, eventDoc);
                eventMap.addEvent(beanEvent);
            }
        }
    }

    public BeanDescriptor getBeanDescriptor() {
        return beanDescriptor;
    }

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

    @Override
    public Class<?> getType() {
        return beanClass;
    }

    @Override
    public IPropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap() {
        return eventMap;
    }

    /** ⇱ Implementaton Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return beanClass.isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return beanClass.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return beanClass.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return beanClass.getDeclaredAnnotations();
    }

    /** ⇱ Implementaton Of {@link IElement}. */
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public int getDetailLevel() {
        return detailLevel;
    }

    @Override
    public int getPriority() {
        return priority;
    }

}
