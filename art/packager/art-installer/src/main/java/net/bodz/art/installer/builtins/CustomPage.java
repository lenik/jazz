package net.bodz.art.installer.builtins;

import static net.bodz.art.installer.util.TreeItems.FULL;
import static net.bodz.art.installer.util.TreeItems.NONE;
import static net.bodz.art.installer.util.TreeItems.UNKNOWN;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.bodz.art.installer.ConfigPage;
import net.bodz.art.installer.IComponent;
import net.bodz.art.installer.IProject;
import net.bodz.art.installer.ISession;
import net.bodz.art.installer.Scheme;
import net.bodz.art.installer.Variable;
import net.bodz.art.installer.nls.PackNLS;
import net.bodz.art.installer.util.MissingDependancyBuffer;
import net.bodz.art.installer.util.MissingDependancyBuffer.Entry;
import net.bodz.art.installer.util.TreeItems;
import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.ui.UserInterface;
import net.bodz.swt.err.QuietValidateException;
import net.bodz.swt.err.ValidateException;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.gui.pfl.ServiceContext;
import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.WindowComposite;

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
public class CustomPage
        extends ConfigPage {

    boolean showRoot = true;

    private Label descriptionLabel;
    private Tree tree;
    private Composite statusbar;
    private Label sizeLabel;
    private WindowComposite basedirsComp;
    private List<Text> dirTexts;

    public CustomPage(IComponent owner, ISession session) {
        super(owner, session);
    }

    @Override
    protected void createContents(Composite holder)
            throws PageException {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 1;
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 3;
        holder.setLayout(gridLayout);

        final Composite infobar = new Composite(holder, SWT.NONE);
        final GridData gd_infobar = new GridData(SWT.LEFT, SWT.FILL, false, true);
        gd_infobar.widthHint = 100;
        infobar.setLayoutData(gd_infobar);
        infobar.setLayout(new FillLayout());

        descriptionLabel = new Label(infobar, SWT.WRAP);
        descriptionLabel.setText("DESCRIPTION"); //$NON-NLS-1$

        Label vt = new Label(holder, SWT.SEPARATOR | SWT.VERTICAL);
        vt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

        tree = new Tree(holder, SWT.CHECK);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean checks = (e.detail & SWT.CHECK) != 0;
                if (checks) {
                    TreeItem item = (TreeItem) e.item;
                    IComponent c = (IComponent) item.getData();
                    if (c.isReadOnly()) {
                        boolean orig = c.getSelection();
                        item.setChecked(orig);
                        e.doit = false;
                        return;
                    }
                    boolean checked = item.getChecked();
                    TreeItems.setState(item, checked ? FULL : NONE, true);
                } else {
                    TreeItem[] items = tree.getSelection();
                    if (items != null && items.length != 0) {
                        TreeItem item = items[0];
                        IComponent c = (IComponent) item.getData();
                        String doc = c.getDoc();
                        if (doc == null)
                            doc = c.getName();
                        descriptionLabel.setText(doc);
                    }
                }
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

        Label hr = new Label(holder, SWT.SEPARATOR | SWT.HORIZONTAL);
        hr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

        final Image dirImage = SWTResources.getImageRes("/com/sun/java/swing/plaf/windows/icons/Directory.gif"); //$NON-NLS-1$
        basedirsComp = new WindowComposite(holder, SWT.NONE, false, holder) {
            @Override
            protected void createContents(Composite parent, int style) {
                final GridLayout gridLayout = new GridLayout();
                gridLayout.numColumns = 3;
                parent.setLayout(gridLayout);

                Map<String, Variable> vardef = session.getProject().getVariables();
                dirTexts = new ArrayList<Text>();
                for (Map.Entry<String, Variable> e : vardef.entrySet()) {
                    String name = e.getKey();
                    final Variable var = e.getValue();
                    if (var.getType() != Variable.BASE_DIR)
                        continue;

                    final Label imageLabel = new Label(parent, SWT.NONE);
                    imageLabel.setImage(dirImage);

                    final Label infoLabel = new Label(parent, SWT.NONE);
                    String s = var.getText();
                    if (var.getDoc() != null)
                        s += ": " + var.getDoc(); //$NON-NLS-1$
                    infoLabel.setText(s);
                    GridData gdInfo = new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1);
                    infoLabel.setLayoutData(gdInfo);

                    new Label(parent, SWT.NONE);

                    final Text dirText = new Text(parent, SWT.BORDER);
                    dirTexts.add(dirText);
                    dirText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
                    File sessionFileValue = (File) session.get(name);
                    if (sessionFileValue == null)
                        throw new NullPointerException(PackNLS.getString("CustomPage.varSetToNull") //$NON-NLS-1$
                                + name);

                    dirText.setData(name);
                    dirText.setText(sessionFileValue.getPath());

                    final Button browseButton = new Button(parent, SWT.NONE);
                    browseButton.setText("..."); //$NON-NLS-1$
                    browseButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.NONE);
                            dialog.setText(PackNLS.getString("CustomPage.selectDirFor") //$NON-NLS-1$
                                    + var.getText());
                            String dir = dialog.open();
                            if (dir != null)
                                dirText.setText(dir);
                        }
                    });
                }
            }

        };
        basedirsComp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
        basedirsComp.setText(PackNLS.getString("CustomPage.installLocations")); //$NON-NLS-1$

        Label hr2 = new Label(holder, SWT.SEPARATOR | SWT.HORIZONTAL);
        hr2.setLayoutData(//
        new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

        statusbar = new Composite(holder, SWT.NONE);
        statusbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
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
    public TreePath service(ServiceContext context)
            throws PageException {
        // refreshBaseDirs();
        refreshTree();
        return null;
    }

    void refreshTree() {
        tree.removeAll();
        // cItemMap = new HashMap<IComponent, TreeItem>();
        IProject project = session.getProject();
        if (showRoot) {
            TreeItem root = new TreeItem(tree, SWT.NONE);
            createTree(root, project);
        } else {
            Collection<? extends IComponent> topNodes = project.getChildren();
            for (IComponent c : topNodes) {
                TreeItem item = new TreeItem(tree, SWT.NONE);
                createTree(item, c);
            }
        }
    }

    static final int iconSize = 16;

    int createTree(TreeItem item, IComponent component) {
        // cItemMap.put(component, item);
        item.setData(component);
        component.setViewData(item);

        String text = component.getText();
        item.setText(text);

        ImageData image = component.getImage();
        if (image != null) {
            if (image.width > iconSize || image.height > iconSize)
                image = image.scaledTo(iconSize, iconSize);
            item.setImage(new Image(pageContainer.getDisplay(), image));
        }

        boolean defaultSelection = component.getSelection();
        Scheme scheme = session.getScheme();
        if (scheme != null)
            defaultSelection = scheme.isIncluded(component);
        int state = defaultSelection ? FULL : NONE;
        Collection<? extends IComponent> children = component.getChildren();
        if (children != null)
            for (IComponent child : children) {
                if (!child.isVisible())
                    continue;
                if (!component.isEnabled())
                    continue;

                boolean readOnly = component.isReadOnly();

                TreeItem childItem = new TreeItem(item, SWT.NONE);
                if (readOnly) {
                    Color readOnlyColor = pageContainer.getDisplay().getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
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
        return PackNLS.getString("CustomPage.title"); //$NON-NLS-1$
    }

    @Override
    public void validate()
            throws ValidateException {
        IProject project = session.getProject();
        MissingDependancyBuffer missingBuffer = new MissingDependancyBuffer();
        project.analyseDependency(missingBuffer);
        List<Entry> missingList = missingBuffer.getList();
        if (!missingList.isEmpty()) {
            SelectComponentsDialog dialog = new SelectComponentsDialog(pageContainer.getShell(), SWT.NONE,
                    PackNLS.getString("CustomPage.checkMissings"), //$NON-NLS-1$
                    PackNLS.getString("CustomPage.checkMissings.caption"), missingList); //$NON-NLS-1$
            Collection<IComponent> add = dialog.open();
            if (add == null)
                throw new QuietValidateException(tree);
            for (IComponent c : add) {
                TreeItem item = (TreeItem) c.getViewData();
                TreeItems.setState(item, FULL);
                c.setSelection(true);
            }
        }

        for (Text dirText : dirTexts) {
            String name = (String) dirText.getData();
            String path = dirText.getText();
            File dirFile = new File(path);
            if (dirFile.isFile())
                throw new ValidateException(dirText, PackNLS.getString("CustomPage.fileExists")); //$NON-NLS-1$
            else if (!dirFile.exists()) {
                UserInterface UI = session.getUserInterface();
                boolean confirmed = UI.confirm(PackNLS.getString("CustomPage.createDirQ"), //$NON-NLS-1$
                        PackNLS.format("CustomPage.confirmMkdir", dirFile));//$NON-NLS-1$
                if (!confirmed) {
                    throw new QuietValidateException(dirText);
                } else if (!dirFile.mkdirs())
                    throw new ValidateException(dirText, PackNLS.getString("CustomPage.cantMkdir") //$NON-NLS-1$
                            + dirFile);
            }
            session.set(name, dirFile);
        }
    }

}
