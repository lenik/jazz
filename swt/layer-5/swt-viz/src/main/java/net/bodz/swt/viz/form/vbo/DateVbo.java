package net.bodz.swt.viz.form.vbo;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.gui.css3.Border;
import net.bodz.bas.gui.css3.ICss3Length;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.IValueChangeSource;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;

public class DateVbo
        extends AbstractSwtViewBuilder<Date> {

    public DateVbo() {
        super(Date.class);
    }

    protected String format(Date date) {
        return String.valueOf(date);
    }

    @Override
    public Widget buildSwtView(Composite parent, final ISwtGUIRefEntry<Date> entry, int styleInt, IOptions options)
            throws ViewBuilderException {
        ISwtControlStyleDeclaration styleDecl = entry.getStyle();

        boolean readOnly = styleDecl.getReadOnly() == Boolean.TRUE;
        Date date = entry.get();

        if (readOnly) {
            final Label dateLabel = new Label(parent, styleInt);
            dateLabel.setText(format(date));
            if (entry.query(IValueChangeSource.class) != null)
                bindProperty(entry, dateLabel, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        Date date = entry.get();
                        dateLabel.setText(format(date));
                        return true;
                    }
                });
            return dateLabel;

        } else {

            Border border = styleDecl.getBorder();
            if (border != null) {
                ICss3Length borderWidth = border.getWidth();
                if (borderWidth != null && borderWidth.getValue() > 0.0)
                    styleInt |= SWT.BORDER;
            }

            Composite holder = new Composite(parent, styleInt);
            holder.setLayout(new GridLayout(2, false));

            final Text text = new Text(holder, styleInt | SWT.READ_ONLY);
            text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            Integer maxLength = styleDecl.getMaxLength();
            if (maxLength != null)
                text.setTextLimit(maxLength);

            text.setText(format(date));
            if (entry.query(IValueChangeSource.class) != null)
                bindProperty(entry, text, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        Date date = entry.get();
                        text.setText(format(date));
                        return true;
                    }
                });

            final Button picker = new Button(holder, SWT.NONE);
            picker.setText("...");
            picker.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    // DatePicker...
                }
            });
            return holder;
        }
    }

}
