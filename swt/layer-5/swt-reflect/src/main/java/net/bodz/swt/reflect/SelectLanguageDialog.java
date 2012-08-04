package net.bodz.swt.reflect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import net.bodz.bas.c.java.util.LocaleTraits;
import net.bodz.bas.err.CreateException;
import net.bodz.swt.c3.dialog.SimpleDialog;
import net.bodz.swt.gui.err.ValidateException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @test
 */
public class SelectLanguageDialog
        extends SimpleDialog {

    private Class<?> declType;
    private List<String> langNames;
    private Combo combo;

    public SelectLanguageDialog(Shell parent, int style, Class<?> declType) {
        super(parent, style, "Select Locale");
        this.declType = declType;
    }

    @Override
    public synchronized String open() {
        return (String) super.open(false);
    }

    @Override
    protected String evaluate()
            throws ValidateException {
        int index = combo.getSelectionIndex();
        if (index == -1)
            throw new ValidateException(combo, "Language isn't selected");
        return langNames.get(index);
    }

    @Override
    protected void createBody(Composite parent)
            throws CreateException {
        langNames = new ArrayList<String>();
        while (declType != null) {
            Language a = declType.getAnnotation(Language.class);
            if (a != null) {
                for (String langName : a.value())
                    langNames.add(langName);
            }
            declType = declType.getSuperclass();
        }
        if (langNames.isEmpty())
            accept(null);
        if (langNames.size() == 1)
            accept(langNames.get(0));

        Collections.sort(langNames);

        combo = new Combo(parent, SWT.READ_ONLY);
        String defaultLocaleName = Locale.getDefault().toString();
        for (int i = 0; i < langNames.size(); i++) {
            String name = langNames.get(i);
            Locale locale = LocaleTraits.parseLocale(name);
            String caption = locale.getDisplayName();
            combo.add(caption);
            if (name.equals(defaultLocaleName))
                combo.select(i);
        }
    }

}
