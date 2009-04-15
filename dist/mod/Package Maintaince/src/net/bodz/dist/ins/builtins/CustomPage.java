package net.bodz.dist.ins.builtins;

import java.io.File;

import net.bodz.bas.rt.Interaction;
import net.bodz.dist.ins.BaseDir;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.controls.DetailComposite;
import net.bodz.swt.gui.SlientValidationException;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

/**
 * @test CustomPageTest
 */
public class CustomPage extends ConfigPage {

    private ISession        session;

    private Label           descriptionLabel;
    private Tree            tree;
    private Composite       statusbar;
    private Label           sizeLabel;
    private DetailComposite basedirsComp;
    private Text[]          dirTexts;

    public CustomPage(ISession _session, Composite parent, int style) {
        super(parent, style);
        this.session = _session;
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 1;
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        final Composite infobar = new Composite(this, SWT.BORDER);
        final GridData gd_infobar = new GridData(SWT.LEFT, SWT.FILL, false,
                true);
        gd_infobar.widthHint = 100;
        infobar.setLayoutData(gd_infobar);
        infobar.setLayout(new FillLayout());

        descriptionLabel = new Label(infobar, SWT.WRAP);
        descriptionLabel.setText("DESCRIPTION"); //$NON-NLS-1$

        tree = new Tree(this, SWT.NONE);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
        tree.addTreeListener(new TreeListener() {
            @Override
            public void treeExpanded(TreeEvent e) {
                // Pre-loaded
            }

            @Override
            public void treeCollapsed(TreeEvent e) {
                // Pre-loaded
            }
        });

        final Image dirImage = SWTResources
                .getImageRes("/com/sun/java/swing/plaf/windows/icons/Directory.gif"); //$NON-NLS-1$
        basedirsComp = new DetailComposite(this, SWT.NONE, false, this) {
            @Override
            protected void createContents(Composite parent, int style) {
                final GridLayout gridLayout = new GridLayout();
                gridLayout.numColumns = 3;
                parent.setLayout(gridLayout);

                BaseDir[] baseDirs = session.getProject().getBaseDirs();
                dirTexts = new Text[baseDirs.length];
                for (int i = 0; i < baseDirs.length; i++) {
                    final BaseDir baseDir = baseDirs[i];

                    final Label imageLabel = new Label(parent, SWT.NONE);
                    imageLabel.setImage(dirImage);

                    final Label infoLabel = new Label(parent, SWT.NONE);
                    String s = baseDir.getDisplayName();
                    if (baseDir.getDescription() != null)
                        s += ": " + baseDir.getDescription(); //$NON-NLS-1$
                    infoLabel.setText(s);
                    GridData gdInfo = new GridData(GridData.BEGINNING,
                            GridData.CENTER, false, false, 2, 1);
                    infoLabel.setLayoutData(gdInfo);

                    new Label(parent, SWT.NONE);

                    final Text dirText = new Text(parent, SWT.BORDER);
                    dirTexts[i] = dirText;
                    dirText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
                            true, false));
                    dirText.setText(baseDir.getPreferred().getPath());

                    final Button browseButton = new Button(parent, SWT.NONE);
                    browseButton.setText("..."); //$NON-NLS-1$
                    browseButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            DirectoryDialog dialog = new DirectoryDialog(
                                    getShell(), SWT.NONE);
                            dialog.setText(PackNLS
                                    .getString("CustomPage.selectDirFor") //$NON-NLS-1$
                                    + baseDir.getDisplayName());
                            String dir = dialog.open();
                            if (dir != null)
                                dirText.setText(dir);
                        }
                    });
                }
            }

        };
        basedirsComp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
                false, 2, 1));
        basedirsComp.setText(PackNLS.getString("CustomPage.installLocations")); //$NON-NLS-1$

        statusbar = new Composite(this, SWT.BORDER);
        statusbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
                2, 1));
        final GridLayout gridLayout_statusbar = new GridLayout();
        gridLayout_statusbar.numColumns = 2;
        gridLayout_statusbar.marginHeight = 0;
        gridLayout_statusbar.marginWidth = 0;
        statusbar.setLayout(gridLayout_statusbar);

        final Label installSizeLabel = new Label(statusbar, SWT.NONE);
        installSizeLabel.setText(PackNLS.getString("CustomPage.installSize")); //$NON-NLS-1$

        sizeLabel = new Label(statusbar, SWT.NONE);
    }

    protected void setSizeBytes(long size) {
        sizeLabel.setText(size + PackNLS.getString("CustomPage.bytes")); //$NON-NLS-1$
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return "Custom Components";
    }

    @Override
    public void validate() throws ValidateException {
        BaseDir[] baseDirs = session.getProject().getBaseDirs();
        for (int i = 0; i < baseDirs.length; i++) {
            String baseName = baseDirs[i].getName();
            Text dirText = dirTexts[i];
            String dir = dirText.getText();
            File dirFile = new File(dir);
            if (dirFile.isFile())
                throw new ValidateException(dirText, PackNLS
                        .getString("CustomPage.fileExists")); //$NON-NLS-1$
            else if (!dirFile.exists()) {
                Interaction iact = session.getInteraction();
                boolean confirmed = iact.confirm(PackNLS
                        .getString("CustomPage.createDirQ"), //$NON-NLS-1$
                        PackNLS.getString("CustomPage.11") + dirFile //$NON-NLS-1$
                                + " isn't existed, shall I create it?"); //$NON-NLS-1$
                if (!confirmed) {
                    throw new SlientValidationException(dirText);
                } else if (!dirFile.mkdirs())
                    throw new ValidateException(dirText, PackNLS
                            .getString("CustomPage.cantMkdir") //$NON-NLS-1$
                            + dirFile);
            }
            session.setBaseDir(baseName, dirFile);
        }
    }

}
