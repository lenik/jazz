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

public class FieldDeclBuilder {

    public MutableFieldDecl build(IProperty property)
            throws ParseException {
        MutableFieldDecl fieldDecl = new MutableFieldDecl();
        populate(fieldDecl, property);
        return fieldDecl;
    }

    void populate(MutableFieldDecl fieldDecl, IProperty property)
            throws ParseException {
        fieldDecl.setProperty(property);
        fieldDecl.setValueType(property.getPropertyType());

        IMutableElement.fn.copy1(property, fieldDecl);

        Class<?> propertyType = property.getPropertyType();
        if (TypeKind.isNumeric(propertyType))
            fieldDecl.setStyleClass("numeric");

        populate(fieldDecl, (IAnnotated) property);

        IElementDoc xjdoc = property.getXjdoc();
        if (xjdoc != null)
            populate(fieldDecl, xjdoc);
    }

    void populate(MutableFieldDecl fieldDecl, IAnnotated annotations) {
        Priority aPriority = annotations.getAnnotation(Priority.class);
        if (aPriority != null)
            fieldDecl.setPriority(aPriority.value());

        DetailLevel aDetailLevel = annotations.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            fieldDecl.setDetailLevel(aDetailLevel.value());

        OfGroup aOfGroup = annotations.getAnnotation(OfGroup.class);
        if (aOfGroup != null) {
            Class<?>[] ofGroups = aOfGroup.value();
            if (ofGroups.length >= 1) {
                FieldCategory category = FieldCategory.fromTagClass(ofGroups[0]);
                fieldDecl.setCategory(category);
            }
        }

        FormInput aFormInput = annotations.getAnnotation(FormInput.class);
        if (aFormInput != null) {
            fieldDecl.preferredSortOrder = aFormInput.sort();
            fieldDecl.readOnly = aFormInput.readOnly();
            fieldDecl.maxLength = aFormInput.maxLength();
            fieldDecl.textWidth = aFormInput.textWidth();
            fieldDecl.echoChar = aFormInput.echoChar();

            String pattern = aFormInput.numberFormat();
            fieldDecl.numberFormat = pattern.isEmpty() ? null : new DecimalFormat(pattern);

            String s = aFormInput.placeholder();
            fieldDecl.placeholder = s.isEmpty() ? null : iString.fn.val(s);

            fieldDecl.nullConvertion = aFormInput.nullconv();
            fieldDecl.spaceNormalization = aFormInput.space();
        }
    }

    void populate(MutableFieldDecl fieldDecl, IElementDoc doc)
            throws ParseException {
        fieldDecl.placeholder = (iString) doc.getTag("placeholder");

        String css = (String) doc.getTag("style");
        if (css != null) {
            UiElementStyleDeclaration styleDecl = new UiElementStyleDeclaration(null);
            styleDecl.parseCss(css);
            fieldDecl.setStyle(styleDecl);
        }
    }

}
