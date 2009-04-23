package net.bodz.swt.gui.pfl;

import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.swt.gui.util.ControlTestApp;
import net.bodz.swt.nls.GUINLS;

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
        wizard.definePage("test", new PageFactory() { //$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return createPage(parent, SWT.NONE);
                    }
                });
        wizard.definePage("end", new PageFactory() { //$NON-NLS-1$
                    @Override
                    public PageComposite create(Composite parent) {
                        return new PageComposite(parent, SWT.NONE) {
                            Label prevLabel;
                            Label infoLabel;
                            Text  text;
                            {
                                setLayout(new GridLayout(1, false));
                                prevLabel = new Label(this, SWT.NONE);
                                prevLabel.setText(GUINLS.getString("PageTestApp.youFromQ")); //$NON-NLS-1$

                                infoLabel = new Label(this, SWT.NONE);

                                text = new Text(this, SWT.BORDER | SWT.MULTI | SWT.READ_ONLY
                                        | SWT.V_SCROLL);
                                GridData gridData = new GridData(GridData.FILL, GridData.FILL,
                                        true, true);
                                text.setLayoutData(gridData);
                            }

                            @Override
                            public void enter(String prev, int reason) {
                                prevLabel.setText(String.format(GUINLS
                                        .getString("PageTestApp.youFromA_ss"), prev, reason)); //$NON-NLS-1$ 

                                Page prevPage = wizard.getPageFlow().getPage(prev);
                                if (prevPage == null)
                                    throw new IllegalUsageException(GUINLS
                                            .getString("PageTestApp.badAddress") //$NON-NLS-1$
                                            + prev);
                                String type = prevPage.getClass().getName();
                                int hash = System.identityHashCode(prevPage);
                                infoLabel
                                        .setText(GUINLS.getString("PageTestApp.page_") + type + ") @" //$NON-NLS-1$ //$NON-NLS-2$
                                                + Integer.toHexString(hash) + ": "); //$NON-NLS-1$

                                String s = prevPage.toString();
                                text.setText(s);
                            }

                            @Override
                            protected Object getInitialState() {
                                return "quit"; //$NON-NLS-1$
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
        pageFlow.putLink("test/next", "end"); //$NON-NLS-1$ //$NON-NLS-2$
        pageFlow.set("test"); //$NON-NLS-1$
    }

    protected abstract PageComposite createPage(Composite parent, int style);

}
