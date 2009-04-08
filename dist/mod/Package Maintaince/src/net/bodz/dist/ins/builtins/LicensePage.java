package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.swt.layouts.BorderLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class LicensePage extends ConfigPage {

    private ISession session;

    private Text     text;

    public LicensePage(ISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;
        setLayout(new BorderLayout(0, 0));

        final Composite composite = new Composite(this, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        composite.setLayoutData(BorderLayout.SOUTH);

        final Button agreeButton = new Button(composite, SWT.RADIO);
        agreeButton.setText("&Agree");
        agreeButton.addSelectionListener(new SetState("next"));

        final Button disagreeButton = new Button(composite, SWT.RADIO);
        disagreeButton.setSelection(true);
        disagreeButton.setText("&Disagree");
        disagreeButton.addSelectionListener(new SetState("cancel"));

        text = new Text(this, SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI
                | SWT.BORDER);
        text.setLayoutData(BorderLayout.CENTER);
    }

    @Override
    public void enter(String prev) {
        String license = session.getProject().getLicense();
        text.setText(license);
    }

    @Override
    protected Object getInitialState() {
        return "cancel";
    }

}
