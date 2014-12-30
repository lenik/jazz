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

public class FieldDefBuilder {

    public MutableFieldDef build(IProperty property)
            throws ParseException {
        MutableFieldDef fieldDef = new MutableFieldDef();
        populate(fieldDef, property);
        return fieldDef;
    }

    void populate(MutableFieldDef fieldDef, IProperty property)
            throws ParseException {
        fieldDef.setProperty(property);
        fieldDef.setValueType(property.getPropertyType());

        IMutableElement.fn.copy1(property, fieldDef);

        Class<?> propertyType = property.getPropertyType();
        if (TypeKind.isNumeric(propertyType))
            fieldDef.setStyleClass("numeric");

        populate(fieldDef, (IAnnotated) property);

        IElementDoc xjdoc = property.getXjdoc();
        if (xjdoc != null)
            populate(fieldDef, xjdoc);
    }

    void populate(MutableFieldDef fieldDef, IAnnotated annotations) {
        Priority aPriority = annotations.getAnnotation(Priority.class);
        if (aPriority != null)
            fieldDef.setPriority(aPriority.value());

        DetailLevel aDetailLevel = annotations.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            fieldDef.setDetailLevel(aDetailLevel.value());

        OfGroup aOfGroup = annotations.getAnnotation(OfGroup.class);
        if (aOfGroup != null) {
            Class<?>[] ofGroups = aOfGroup.value();
            if (ofGroups.length >= 1) {
                FieldCategory fieldGroup = FieldCategory.fromTagClass(ofGroups[0]);
                fieldDef.setFieldGroup(fieldGroup);
            }
        }

        FormInput aFormField = annotations.getAnnotation(FormInput.class);
        if (aFormField != null) {
            fieldDef.preferredSortOrder = aFormField.sort();
            fieldDef.readOnly = aFormField.readOnly();
            fieldDef.maxLength = aFormField.maxLength();
            fieldDef.textWidth = aFormField.textWidth();
            fieldDef.echoChar = aFormField.echoChar();

            String pattern = aFormField.numberFormat();
            fieldDef.numberFormat = pattern.isEmpty() ? null : new DecimalFormat(pattern);

            String s = aFormField.placeholder();
            fieldDef.placeholder = s.isEmpty() ? null : iString.fn.val(s);

            fieldDef.nullConvertion = aFormField.nullconv();
            fieldDef.spaceNormalization = aFormField.space();
        }
    }

    void populate(MutableFieldDef fieldDef, IElementDoc doc)
            throws ParseException {
        fieldDef.placeholder = (iString) doc.getTag("placeholder");

        String css = (String) doc.getTag("style");
        if (css != null) {
            UiElementStyleDeclaration styleDecl = new UiElementStyleDeclaration(null);
            styleDecl.parseCss(css);
            fieldDef.setStyle(styleDecl);
        }
    }

}
