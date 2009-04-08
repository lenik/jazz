package net.bodz.swt.gui.pfl;

import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public abstract class PageTestApp extends ControlTestApp {

    WizardComposite wizard;

    public PageTestApp() {
        wizard = new WizardComposite(parent, SWT.NONE);
        wizard.definePage("test", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return createPage(parent, SWT.NONE);
            }
        });
        wizard.definePage("end", new PageFactory() {
            @Override
            public PageComposite create(Composite parent) {
                return new PageComposite(parent, SWT.NONE) {
                    Label prevLabel;
                    Label infoLabel;
                    Text  text;
                    {
                        setLayout(new GridLayout(1, false));
                        prevLabel = new Label(this, SWT.NONE);
                        prevLabel.setText("You're come from ?");

                        infoLabel = new Label(this, SWT.NONE);

                        text = new Text(this, SWT.BORDER | SWT.MULTI
                                | SWT.READ_ONLY | SWT.V_SCROLL);
                        GridData gridData = new GridData(GridData.FILL,
                                GridData.FILL, true, true);
                        text.setLayoutData(gridData);
                    }

                    @Override
                    public void enter(String prev) {
                        prevLabel.setText("You're come from `" + prev + "'. ");

                        Page page = wizard.getPageFlow().getPage(prev);
                        if (page == null)
                            throw new IllegalUsageException("bad address: "
                                    + prev);
                        String type = page.getClass().getName();
                        int hash = System.identityHashCode(page);
                        infoLabel.setText("Page(" + type + ") @"
                                + Integer.toHexString(hash) + ": ");

                        String s = page.toString();
                        text.setText(s);
                    }

                    @Override
                    protected Object getInitialState() {
                        return "quit";
                    }
                };
            }
        });
        wizard.addExitListener(new WizardExitListener() {
            @Override
            public void wizardExit(WizardExitEvent e) {
                shell.dispose();
            }
        });
        SymlinkPageFlow pageFlow = wizard.getPageFlow();
        pageFlow.putLink("test/next", "end");
        pageFlow.set("test");
    }

    protected abstract PageComposite createPage(Composite parent, int style);

}
