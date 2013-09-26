package net.bodz.pkg.installer.builtins;

import static net.bodz.pkg.installer.nls.PackNLS.PackNLS;
import static net.bodz.pkg.installer.util.TreeItems.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import org.eclipse.swt.widgets.*;

import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.err.GUIValidationException;
import net.bodz.bas.gui.err.QuietValidationException;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.gui.style.IImageData;
import net.bodz.bas.gui.style.ImageUsage;
import net.bodz.bas.gui.xjdoc.GUIElementDoc;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;
import net.bodz.pkg.installer.ConfigPage;
import net.bodz.pkg.installer.IComponent;
import net.bodz.pkg.installer.IProject;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.Scheme;
import net.bodz.pkg.installer.Variable;
import net.bodz.pkg.installer.util.MissingDependancyBuffer;
import net.bodz.pkg.installer.util.MissingDependancyBuffer.Entry;
import net.bodz.pkg.installer.util.TreeItems;
import net.bodz.swt.c.composite.WindowComposite;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.ServiceContext;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.gui.style.SwtImageMapper;

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
        descriptionLabel.setText("DESCRIPTION");

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
                        boolean orig = c.isSelected();
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
                        iString text = c.getDescription();
                        if (text != null)
                            descriptionLabel.setText(text.toString());
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

        final Image dirImage = SWTResources.getImageRes("/com/sun/java/swing/plaf/windows/icons/Directory.gif");
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
                        s += ": " + var.getDoc();
                    infoLabel.setText(s);
                    GridData gdInfo = new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1);
                    infoLabel.setLayoutData(gdInfo);

                    new Label(parent, SWT.NONE);

                    final Text dirText = new Text(parent, SWT.BORDER);
                    dirTexts.add(dirText);
                    dirText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
                    File sessionFileValue = (File) session.get(name);
                    if (sessionFileValue == null)
                        throw new NullPointerException(tr._("file variable is set to null in session: ") + name);

                    dirText.setData(name);
                    dirText.setText(sessionFileValue.getPath());

                    final Button browseButton = new Button(parent, SWT.NONE);
                    browseButton.setText("...");
                    browseButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.NONE);
                            dialog.setText(tr._("Select the directory for ") + var.getText());
                            String dir = dialog.open();
                            if (dir != null)
                                dirText.setText(dir);
                        }
                    });
                }
            }

        };
        basedirsComp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
        basedirsComp.setText(tr._("Install Locations:"));

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
        installSizeLabel.setText(tr._("Install Size: "));

        sizeLabel = new Label(statusbar, SWT.NONE);
    }

    @Override
    public PathEntries service(ServiceContext context)
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

        iString text = component.getDescription();
        if (text != null)
            item.setText(text.toString());

        if (component instanceof IXjdocElement) {
            IJavaElementDoc xjdoc = ((IXjdocElement) component).getXjdoc();
            GUIElementDoc guidoc = xjdoc.to(GUIElementDoc.class);
            IGUIElementStyleDeclaration styleClass = guidoc.getStyleClass();

            IImageData imageData = styleClass.getImage(ImageUsage.NORMAL);
            if (imageData != null) {
                if (imageData.getWidth() > iconSize || imageData.getHeight() > iconSize)
                    imageData = imageData.scaledTo(iconSize, iconSize);

                Image _image = SwtImageMapper.convert(pageContainer.getDisplay(), imageData);
                item.setImage(_image);
            }
        }

        boolean defaultSelection = component.isSelected();
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
        sizeLabel.setText(size + tr._(" bytes"));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return tr._("Custom Components");
    }

    @Override
    public void validate()
            throws GUIValidationException {
        IProject project = session.getProject();
        MissingDependancyBuffer missingBuffer = new MissingDependancyBuffer();
        project.analyseDependency(missingBuffer);
        List<Entry> missingList = missingBuffer.getList();
        if (!missingList.isEmpty()) {
            SelectComponentsDialog dialog = new SelectComponentsDialog(pageContainer.getShell(), SWT.NONE,
                    tr._("Check missing components"),
                    tr._("The following missing components are required due to dependancy:"), missingList);
            Collection<IComponent> add = dialog.open();
            if (add == null)
                throw new QuietValidationException(tree);
            for (IComponent c : add) {
                TreeItem item = (TreeItem) c.getViewData();
                TreeItems.setState(item, FULL);
                c.setSelected(true);
            }
        }

        for (Text dirText : dirTexts) {
            String name = (String) dirText.getData();
            String path = dirText.getText();
            File dirFile = new File(path);
            if (dirFile.isFile())
                throw new GUIValidationException(dirText, tr._("file exists"));
            else if (!dirFile.exists()) {
                IUserDialogs dialogs = session.getUserDialogs();
                boolean confirmed = dialogs.confirm(tr._("Create the directory?"),
                        PackNLS.format("CustomPage.confirmMkdir", dirFile));
                if (!confirmed) {
                    throw new QuietValidationException(dirText);
                } else if (!dirFile.mkdirs())
                    throw new GUIValidationException(dirText, tr._("Can\'t create ") + dirFile);
            }
            session.set(name, dirFile);
        }
    }

}
