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
import net.bodz.bas.repr.form.meta.IndexColumn;
import net.bodz.bas.repr.form.meta.ListInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.ui.style.UiElementStyleDeclaration;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class FieldDeclBuilder {

    public IFieldDecl build(IProperty property)
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

        IndexColumn aIndexColumn = annotations.getAnnotation(IndexColumn.class);
        if (aIndexColumn != null) {
            int maxLength = aIndexColumn.maxLength();
            fieldDecl.columnMaxLength = maxLength == -1 ? null : maxLength;
            fieldDecl.columnVisibility = aIndexColumn.display() ? aIndexColumn.visible() ? true : false : false;
        }

        FormInput aFormInput = annotations.getAnnotation(FormInput.class);
        if (aFormInput != null) {
            String inputName = aFormInput.name();
            fieldDecl.face = aFormInput.face();
            fieldDecl.inputName = inputName.isEmpty() ? null : inputName;

            fieldDecl.readOnly = aFormInput.readOnly();
            fieldDecl.textWidth = aFormInput.textWidth();

            String s = aFormInput.inputMask();
            fieldDecl.inputMask = s.isEmpty() ? null : s;

            s = aFormInput.placeholder();
            fieldDecl.placeholder = iString.fn.val(s.isEmpty() ? null : s);

            fieldDecl.nullConvertion = aFormInput.nullconv();
        }

        TextInput aTextInput = annotations.getAnnotation(TextInput.class);
        if (aTextInput != null) {
            fieldDecl.maxLength = aTextInput.maxLength();
            fieldDecl.echoChar = aTextInput.echoChar();
            fieldDecl.spaceNormalization = aTextInput.space();
        }

        NumericInput aNumericInput = annotations.getAnnotation(NumericInput.class);
        if (aNumericInput != null) {
            double min = aNumericInput.min();
            double max = aNumericInput.max();
            double step = aNumericInput.step();
            String pattern = aNumericInput.numberFormat();
            fieldDecl.minValue = min == Double.MIN_VALUE ? null : min;
            fieldDecl.maxValue = max == Double.MAX_VALUE ? null : max;
            fieldDecl.stepValue = step == Double.NaN ? null : step;
            fieldDecl.numberFormat = pattern.isEmpty() ? null : new DecimalFormat(pattern);
        }

        ListInput aListInput = annotations.getAnnotation(ListInput.class);
        if (aListInput != null) {
            fieldDecl.itemSortOrder = aListInput.sort();
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
