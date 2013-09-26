package net.bodz.swt.c.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.c.java.util.LocaleTypers;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.gui.err.GUIValidationException;
import net.bodz.mda.xjdoc.conv.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.artifact.ArtifactDoc;

public class SelectLanguageDialog
        extends SimpleDialog {

    private List<String> langNames;
    private Combo combo;

    public SelectLanguageDialog(Shell parent, int style, Class<?> declType) {
        super(parent, style, "Select Locale");

        langNames = new ArrayList<String>();

        while (declType != null) {
            ClassDoc classDoc = ClassDocLoader.load(declType);
            if (classDoc != null) {
                ArtifactDoc typeDoc = classDoc.to(ArtifactDoc.class);
                for (String lang : typeDoc.getUsedLangs())
                    if (lang != null)
                        langNames.add(lang);
            }

            declType = declType.getSuperclass();
        }

        if (langNames.isEmpty())
            langNames.add("zh-CN"); // TODO default language list: C, en-us.
    }

    @Override
    public synchronized String open() {
        return (String) super.open(false);
    }

    @Override
    protected String evaluate()
            throws GUIValidationException {
        int index = combo.getSelectionIndex();
        if (index == -1)
            throw new GUIValidationException(combo, "Language isn't selected");
        return langNames.get(index);
    }

    @Override
    protected void createBody(Composite parent)
            throws CreateException {
        Collections.sort(langNames);

        String defaultLocaleName = Locale.getDefault().toString();

        combo = new Combo(parent, SWT.READ_ONLY);
        for (int i = 0; i < langNames.size(); i++) {
            String langName = langNames.get(i);
            Locale locale = LocaleTypers.parseLocale(langName);
            String caption = locale.getDisplayName();
            combo.add(caption);
            if (langName.equals(defaultLocaleName))
                combo.select(i);
        }

        if (langNames.size() == 1)
            combo.select(0);
    }

}
