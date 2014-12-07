package net.bodz.bas.repr.form;

import java.text.DecimalFormat;

import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IMutableElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.IAnnotated;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.ui.style.UiElementStyleDeclaration;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class FormFieldBuilder {

    public MutableFormField build(IProperty property)
            throws ParseException {
        MutableFormField formField = new MutableFormField();
        populate(formField, property);
        return formField;
    }

    void populate(MutableFormField formField, IProperty property)
            throws ParseException {
        formField.setAccessor(property);
        formField.setValueType(property.getPropertyType());

        IMutableElement.fn.copy1(property, formField);

        Class<?> propertyType = property.getPropertyType();
        if (TypeKind.isNumeric(propertyType))
            formField.setStyleClass("numeric");

        populate(formField, (IAnnotated) property);

        IElementDoc xjdoc = property.getXjdoc();
        if (xjdoc != null)
            populate(formField, xjdoc);
    }

    void populate(MutableFormField formField, IAnnotated annotations) {
        Priority aPriority = annotations.getAnnotation(Priority.class);
        if (aPriority != null)
            formField.setPriority(aPriority.value());

        DetailLevel aDetailLevel = annotations.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            formField.setDetailLevel(aDetailLevel.value());

        OfGroup aOfGroup = annotations.getAnnotation(OfGroup.class);
        if (aOfGroup != null) {
            Class<?>[] ofGroups = aOfGroup.value();
            if (ofGroups.length >= 1) {
                FieldGroup fieldGroup = FieldGroup.forClass(ofGroups[0]);
                formField.setFieldGroup(fieldGroup);
            }
        }

        FormInput aFormField = annotations.getAnnotation(FormInput.class);
        if (aFormField != null) {
            formField.preferredSortOrder = aFormField.sort();
            formField.readOnly = aFormField.readOnly();
            formField.maxLength = aFormField.maxLength();
            formField.textWidth = aFormField.textWidth();
            formField.echoChar = aFormField.echoChar();

            String pattern = aFormField.numberFormat();
            formField.numberFormat = pattern.isEmpty() ? null : new DecimalFormat(pattern);

            String s = aFormField.placeholder();
            formField.placeholder = s.isEmpty() ? null : iString.fn.val(s);

            formField.nullConvertion = aFormField.nullconv();
            formField.spaceNormalization = aFormField.space();
        }
    }

    void populate(MutableFormField formField, IElementDoc doc)
            throws ParseException {
        formField.placeholder = (iString) doc.getTag("placeholder");

        String css = (String) doc.getTag("style");
        if (css != null) {
            UiElementStyleDeclaration styleDecl = new UiElementStyleDeclaration(null);
            styleDecl.parseCss(css);
            formField.setStyle(styleDecl);
        }
    }

}
