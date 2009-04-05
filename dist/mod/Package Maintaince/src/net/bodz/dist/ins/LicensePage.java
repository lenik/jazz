package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

public class LicensePage extends PageComposite {

    private IProject config;

    private Text          text;

    public LicensePage(IProject config, Composite parent, int style) {
        super(parent, style);
        this.config = config;
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
        String license = config.getLicense();
        text.setText(license);
    }

    @Override
    protected Object getInitialState() {
        return "cancel";
    }

}
