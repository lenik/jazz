package net.bodz.swt.c3.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.c.java.util.LocaleTraits;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.gui.err.GUIValidationException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

public class SelectLanguageDialog
        extends SimpleDialog {

    private List<String> langNames;
    private Combo combo;

    public SelectLanguageDialog(Shell parent, int style, Class<?> declType) {
        super(parent, style, "Select Locale");

        langNames = new ArrayList<String>();

        while (declType != null) {
            ArtifactDoc typeDoc = ClassDocs.loadFromResource(declType).as(ArtifactDoc.class);

            for (String lang : typeDoc.getUsedLangs())
                langNames.add(lang);

            declType = declType.getSuperclass();
        }
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

        if (langNames.isEmpty())
            accept(null);
        if (langNames.size() == 1)
            accept(langNames.get(0));

        Collections.sort(langNames);

        combo = new Combo(parent, SWT.READ_ONLY);
        String defaultLocaleName = Locale.getDefault().toString();
        for (int i = 0; i < langNames.size(); i++) {
            String langName = langNames.get(i);
            Locale locale = LocaleTraits.parseLocale(langName);
            String caption = locale.getDisplayName();
            combo.add(caption);
            if (langName.equals(defaultLocaleName))
                combo.select(i);
        }
    }

}
