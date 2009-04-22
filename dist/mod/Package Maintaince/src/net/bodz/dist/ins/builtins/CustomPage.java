package net.bodz.dist.ins.builtins;

import static net.bodz.dist.ins.builtins.TreeItems.FULL;
import static net.bodz.dist.ins.builtins.TreeItems.NONE;
import static net.bodz.dist.ins.builtins.TreeItems.UNKNOWN;

import java.io.File;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.ui.UserInterface;
import net.bodz.dist.ins.BaseDir;
import net.bodz.dist.ins.Component;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Project;
import net.bodz.dist.ins.Scheme;
import net.bodz.dist.ins.util.MissingDependancyBuffer;
import net.bodz.dist.ins.util.MissingDependancyBuffer.Entry;
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.controls.WindowComposite;
import net.bodz.swt.gui.SlientValidationException;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.gui.pfl.Location;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.TreeItem;

/**
 * @test CustomPageTest
 */
public class CustomPage extends ConfigPage {

    private ISession        session;

    boolean                 showRoot = true;

    private Label           descriptionLabel;
    private Tree            tree;
    private Composite       statusbar;
    private Label           sizeLabel;
    private WindowComposite basedirsComp;
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

        tree = new Tree(this, SWT.CHECK);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if ((e.detail & SWT.CHECK) == 0)
                    return;
                TreeItem item = (TreeItem) e.item;
                Component c = (Component) item.getData();
                if (c.isReadOnly()) {
                    boolean orig = c.getSelection();
                    item.setChecked(orig);
                    e.doit = false;
                    return;
                }
                boolean checked = item.getChecked();
                TreeItems.setState(item, checked ? FULL : NONE, true);
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
        basedirsComp = new WindowComposite(this, SWT.NONE, false, this) {
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
                    File file = session.getBaseDir(baseDir.getName());
                    dirText.setText(file.getPath());

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

    @Override
    public void enter(String prev, int reason) {
        if (reason != Location.FORWARD)
            return;
        // refreshBaseDirs();
        refreshTree();
    }

    void refreshTree() {
        tree.removeAll();
        // cItemMap = new HashMap<IComponent, TreeItem>();
        Project project = session.getProject();
        if (showRoot) {
            TreeItem root = new TreeItem(tree, SWT.NONE);
            createTree(root, project);
        } else {
            Collection<? extends Component> topNodes = project.getChildren();
            for (Component c : topNodes) {
                TreeItem item = new TreeItem(tree, SWT.NONE);
                createTree(item, c);
            }
        }
    }

    int createTree(TreeItem item, Component component) {
        // cItemMap.put(component, item);
        item.setData(component);
        component.setViewData(item);

        ImageData image = component.getImage();
        String text = component.getText();
        if (image != null)
            item.setImage(new Image(getDisplay(), image));
        item.setText(text);

        boolean defaultSelection = component.getSelection();
        Scheme scheme = session.getScheme();
        if (scheme != null)
            defaultSelection = scheme.isIncluded(component);
        int state = defaultSelection ? FULL : NONE;
        Collection<? extends Component> children = component.getChildren();
        if (children != null)
            for (Component child : children) {
                if (!child.isVisible())
                    continue;
                if (!component.isEnabled())
                    continue;

                boolean readOnly = component.isReadOnly();

                TreeItem childItem = new TreeItem(item, SWT.NONE);
                if (readOnly) {
                    Color readOnlyColor = getDisplay().getSystemColor(
                            SWT.COLOR_WIDGET_DARK_SHADOW);
                    childItem.setForeground(readOnlyColor);
                }

                int cs = createTree(childItem, child);
                state = TreeItems.stateAdd(state, cs);
            }
        if (state == UNKNOWN)
            state = FULL;
        TreeItems.setState(item, state);
        return state;
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
        Project project = session.getProject();
        MissingDependancyBuffer missingBuffer = new MissingDependancyBuffer();
        project.analyseDependency(missingBuffer);
        List<Entry> missingList = missingBuffer.getList();
        if (!missingList.isEmpty()) {
            SelectComponentsDialog dialog = new SelectComponentsDialog(
                    getShell(),
                    SWT.NONE,
                    "Check missing components",
                    "The following missing components are required due to dependancy:",
                    missingList);
            Collection<Component> add = dialog.open();
            if (add == null)
                throw new SlientValidationException(tree);
            for (Component c : add) {
                TreeItem item = (TreeItem) c.getViewData();
                TreeItems.setState(item, FULL);
                c.setSelection(true);
            }
        }

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
                UserInterface UI = session.getUserInterface();
                boolean confirmed = UI.confirm(PackNLS
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
