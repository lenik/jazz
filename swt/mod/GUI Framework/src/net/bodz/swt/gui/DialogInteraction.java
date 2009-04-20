package net.bodz.swt.gui;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.lang.err.CancelException;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.Objects;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.ui.IProposal;
import net.bodz.bas.ui.RenderException;
import net.bodz.bas.ui._Interaction;
import net.bodz.swt.controls.helper.StackComposite;
import net.bodz.swt.dialogs.SimpleDialog;
import net.bodz.swt.gui.styles.base.SWTStrategy;
import net.bodz.swt.gui.styles.grid.SWTGridStrategy;
import net.bodz.swt.nls.GUINLS;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @test {@link DialogInteractionTest}
 */
public class DialogInteraction extends _Interaction {

    static SWTGridStrategy strategy = new SWTGridStrategy();

    private final Shell    parent;
    private final int      style;

    private Image          icon;
    private boolean        cancelable;

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public DialogInteraction(Shell parent, int style) {
        this.parent = parent;
        this.style = style;
    }

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public DialogInteraction(int style) {
        this(new Shell(), style);
    }

    public DialogInteraction(Shell parent) {
        this(parent, SWT.NONE);
    }

    public DialogInteraction() {
        this(SWT.NONE);
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public static final String   ERROR       = "error";      //$NON-NLS-1$
    public static final String   INFORMATION = "information"; //$NON-NLS-1$
    public static final String   QUESTION    = "question";   //$NON-NLS-1$
    public static final String   WARNING     = "warning";    //$NON-NLS-1$
    public static final String   WORKING     = "working";    //$NON-NLS-1$

    // key -> icon image res path.
    static final TextMap<String> aliases;
    static {
        aliases = new HashTextMap<String>();
        aliases.put(ERROR, ""); //$NON-NLS-1$
        aliases.put(INFORMATION, ""); //$NON-NLS-1$
        aliases.put(QUESTION, ""); //$NON-NLS-1$
        aliases.put(WARNING, ""); //$NON-NLS-1$
        aliases.put(WORKING, ""); //$NON-NLS-1$
    }

    public void setIcon(String alias) {
        String respath = aliases.get(alias);
        if (respath == null)
            throw new IllegalArgumentException(GUINLS
                    .getString("SWTInteraction.badicon") //$NON-NLS-1$
                    + alias);
        Image image = SWTResources.getImageRes(respath);
        setIcon(image);
    }

    abstract class _Dialog extends SimpleDialog {

        class RC extends SWTRenderContext {
            @Override
            public void addAction(final IAction action) {
                if (action == null)
                    throw new NullPointerException("action"); //$NON-NLS-1$
                Composite userBar = getUserBar();
                if (userBar == null)
                    throw new IllegalStateException("no user bar"); //$NON-NLS-1$
                final Button button = new Button(userBar, SWT.NONE);
                button.setEnabled(action.isEnabled());
                button.setVisible(action.isVisible());
                String text = action.getText();
                String doc = action.getDoc();
                Image image = action.getImage();
                if (text == null)
                    text = A_bas.getDisplayName(action.getClass());
                button.setText(text);
                if (doc != null)
                    button.setToolTipText(doc);
                if (image != null)
                    button.setImage(image);
                button.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        action.execute();
                    }
                });
            }
        }

        public _Dialog(Shell parent, int style, String title) {
            super(parent, style, title);
        }

        @Override
        protected void createUserButtons(Composite parent)
                throws CreateException {
            // already created.
        }

        @Override
        protected void addEffects() {
            DialogInteraction.this.addEffects(getShell());
        }

        public SWTRenderContext getRenderContext() {
            return new RC();
        }

    }

    protected void addEffects(Shell shell) {
    }

    @Override
    public void alert(String title, final Object detail) {
        SimpleDialog dialog = new _Dialog(parent, style, title) {
            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                SWTRenderContext rc = getRenderContext();
                try {
                    strategy.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createButtons(Composite parent) throws SWTException,
                    CreateException {
                addOKButton(parent);
            }
        };
        try {
            dialog.open();
        } catch (CancelException e) {
            // ignore
        }
    }

    @Override
    public boolean confirm(String title, final Object detail) {
        SimpleDialog dialog = new _Dialog(parent, style, title) {
            SWTRenderContext rc = getRenderContext();

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    strategy.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createButtons(Composite parent) throws SWTException,
                    CreateException {
                addYesButton(parent);
                addNoButton(parent);
            }
        };
        try {
            Object result = dialog.open();
            return (Boolean) result;
        } catch (CancelException e) {
            return false;
        }
    }

    @Override
    public int ask(String title, final Object detail,
            final IProposal... proposals) {
        SimpleDialog dialog = new _Dialog(parent, style, title) {
            SWTRenderContext rc = getRenderContext();

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    strategy.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createButtons(Composite parent) throws SWTException,
                    CreateException {
                for (int i = 0; i < proposals.length; i++) {
                    IProposal p = proposals[i];
                    Image image = null;
                    // if (IProposal instanceof GUIProposal) ...
                    String name = p.getName();
                    // if (bundle.contains(name)) ... else
                    String text = Strings.ucfirstWords(name);
                    char c = p.getMnemonic();
                    int m = Strings.indexOfIgnoreCase(text, c);
                    if (m != -1)
                        text = text.substring(0, m) + "&" + text.substring(m);
                    Button button = addButton(parent, SWT.NONE, image, text, i,
                            false);
                    String description = p.getDescription();
                    if (description != null)
                        button.setToolTipText(description);
                    if (i == 0)
                        button.setSelection(true);
                }
            }
        };
        try {
            Object result = dialog.open();
            return (Integer) result;
        } catch (CancelException e) {
            return -1;
        }
    }

    @Override
    public <T> T prompt(String title, final Object detail, final Class<T> type,
            final T initial) {
        final TypeParser parser;
        try {
            parser = TypeParsers.guess(type, true);
        } catch (ParseException e) {
            throw new IllegalUsageError(GUINLS
                    .getString("SWTInteraction.errparse") + type); //$NON-NLS-1$
        }
        SimpleDialog dialog = new _Dialog(parent, style, title) {
            private Text text;

            @Override
            protected void createDetail(Composite parent) throws SWTException,
                    CreateException {
                SWTRenderContext rc = getRenderContext();
                try {
                    strategy.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent) throws CreateException {
                text = new Text(parent, SWT.BORDER);
                String initialStr = String.valueOf(initial);
                text.setText(initialStr);
                text.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetDefaultSelected(SelectionEvent e) {
                        if ((e.detail & SWT.CANCEL) != 0)
                            throw new CancelException();
                        accept();
                    }
                });
                // text.setSelection(0, initialStr.length());
                text.selectAll();
                text.setFocus();
            }

            @Override
            protected void execute() {
                String s = text.getText();
                try {
                    Object value = parser.parse(s);
                    set(value);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        try {
            @SuppressWarnings("unchecked")
            T result = (T) dialog.open();
            return result;
        } catch (CancelException e) {
            return null;
        }
    }

    private static int FLATMAX = 3;

    static class PreRenderred {
        private final SWTRenderContext rc;
        private final SWTStrategy      style;
        private final StackComposite   stack;
        private final Control[]        controls;
        private int                    next;

        public PreRenderred(SWTRenderContext rc, Composite parent,
                SWTStrategy style, int size) {
            this.rc = rc;
            this.stack = new StackComposite(parent, SWT.BORDER);
            this.style = style;
            this.controls = new Control[size];
        }

        public int render(Object value) throws RenderException, SWTException {
            Control control = style.render(rc, value, stack, SWT.NONE);
            controls[next] = control;
            return next++;
        }

        public void show(int index) {
            stack.bringFront(controls[index]);
        }
    }

    static boolean hasDetail(Iterable<?> values) {
        for (Object val : values) {
            if (val == null)
                continue;
            Class<?> type = val.getClass();
            if (type.isPrimitive())
                continue;
            if (type == String.class)
                continue;
            return true;
        }
        return false;
    }

    @Override
    public <K> K choice(String title, final Object detail,
            final Map<K, ?> candidates, final K initial) {
        SimpleDialog dialog = new _Dialog(parent, style, title) {
            SWTRenderContext rc = getRenderContext();

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    strategy.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent) throws CreateException {
                if (candidates.size() <= FLATMAX)
                    createRadios(parent, this, candidates, initial);
                else
                    createCombo(parent, this, candidates, initial);
            }

            void createRadios(Composite parent, final Ref<Object> result,
                    Map<?, ?> candidates, Object initial)
                    throws CreateException {
                parent.setLayout(new GridLayout(2, false));
                for (Entry<?, ?> entry : candidates.entrySet()) {
                    final Object key = entry.getKey();
                    Object value = entry.getValue();
                    boolean selected = Objects.equals(key, initial);
                    Button radio = new Button(parent, SWT.RADIO);
                    radio.setSelection(selected);
                    try {
                        strategy.render(rc, value, parent, SWT.NONE);
                    } catch (RenderException e) {
                        throw new CreateException(e);
                    }
                    radio.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            result.set(key);
                        }
                    });
                }
            }

            void createCombo(Composite parent, final Ref<Object> result,
                    Map<?, ?> candidates, final Object initial)
                    throws CreateException {
                FillLayout fillLayout = new FillLayout();
                fillLayout.type = SWT.VERTICAL;
                parent.setLayout(fillLayout);

                final Combo combo = new Combo(parent, SWT.READ_ONLY);
                combo.setFocus();

                int size = candidates.size();
                final Object[] keys = new Object[size];
                boolean hasDetail = hasDetail(candidates.values());
                final PreRenderred preRenderred = hasDetail ? new PreRenderred(
                        rc, parent, strategy, size) : null;
                try {
                    int index = 0;
                    for (Entry<?, ?> entry : candidates.entrySet()) {
                        keys[index++] = entry.getKey();
                        if (hasDetail)
                            preRenderred.render(entry.getValue());
                    }
                } catch (RenderException e) {
                    throw new CreateException(e);
                }
                combo.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        int index = combo.getSelectionIndex();
                        if (index != -1) {
                            result.set(keys[index]);
                            if (preRenderred != null)
                                preRenderred.show(index);
                        }
                    }

                    @Override
                    public void widgetDefaultSelected(SelectionEvent e) {
                        widgetSelected(e);
                        accept();
                    }
                });

                // add entries
                for (Entry<?, ?> entry : candidates.entrySet()) {
                    final Object key = entry.getKey();
                    Object value = entry.getValue();
                    boolean selected = Objects.equals(key, initial);
                    int insertIndex = combo.getItemCount();
                    combo.add(String.valueOf(value));
                    if (selected)
                        combo.select(insertIndex);
                }
            }
        };
        dialog.set(initial);
        try {
            @SuppressWarnings("unchecked")
            K key = (K) dialog.open();
            return key;
        } catch (CancelException e) {
            return null;
        }
    }

    @Override
    public <K> Set<K> choices(String title, final Object detail,
            final Map<K, ?> candidates, K... initial) {
        final Set<K> initials = new HashSet<K>(initial.length);

        for (K k : initial)
            initials.add(k);
        SimpleDialog dialog = new _Dialog(parent, style, title) {
            SWTRenderContext rc = getRenderContext();
            Ref<Set<K>>      selection;

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    strategy.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent) throws CreateException {
                if (candidates.size() <= FLATMAX)
                    selection = createChecks(parent, candidates, initials);
                else
                    selection = createList(parent, candidates, initials);
            }

            @Override
            protected void execute() throws CheckException {
                Set<K> set = selection.get();
                set(set);
            }

            Ref<Set<K>> createChecks(Composite parent, Map<K, ?> candidates,
                    Set<K> initial) throws CreateException {
                parent.setLayout(new GridLayout(2, false));

                class KB {
                    K      key;
                    Button button;

                    public KB(K key, Button button) {
                        super();
                        this.key = key;
                        this.button = button;
                    }
                }
                final int size = candidates.size();
                final KB[] checks = new KB[size];
                int index = 0;
                for (Entry<K, ?> entry : candidates.entrySet()) {
                    final K key = entry.getKey();
                    Object value = entry.getValue();
                    boolean selected = initial.contains(value);
                    final Button check = new Button(parent, SWT.CHECK);
                    check.setSelection(selected);
                    checks[index++] = new KB(key, check);
                    try {
                        strategy.render(rc, value, parent, SWT.NONE);
                    } catch (RenderException e) {
                        throw new CreateException(e);
                    }
                }
                return new Ref<Set<K>>() {
                    @Override
                    public Set<K> get() {
                        Set<K> set = new HashSet<K>(size);
                        for (KB kb : checks)
                            if (kb.button.getSelection())
                                set.add(kb.key);
                        return set;
                    }

                    @Override
                    public void set(Object value) {
                        throw new ReadOnlyException();
                    }
                };
            }

            Ref<Set<K>> createList(Composite parent, Map<K, ?> candidates,
                    Set<K> initial) throws CreateException {
                FillLayout fillLayout = new FillLayout();
                fillLayout.type = SWT.VERTICAL;
                parent.setLayout(fillLayout);

                final List listBox = new List(parent, SWT.MULTI);
                listBox.setFocus();

                class _K {
                    K key;

                    public _K(K key) {
                        this.key = key;
                    }
                }

                final int size = candidates.size();
                boolean hasDetail = hasDetail(candidates.values());
                final PreRenderred preRenderred = hasDetail ? new PreRenderred(
                        rc, parent, strategy, size) : null;
                final _K[] keys = new _K[size];
                int index = 0;
                for (Entry<K, ?> entry : candidates.entrySet())
                    try {
                        if (hasDetail)
                            preRenderred.render(entry.getValue());
                        keys[index++] = new _K(entry.getKey());
                    } catch (RenderException e) {
                        throw new CreateException(e);
                    }
                listBox.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        int index = listBox.getSelectionIndex();
                        if (preRenderred != null)
                            preRenderred.show(index);
                    }

                    @Override
                    public void widgetDefaultSelected(SelectionEvent e) {
                        widgetSelected(e);
                        accept();
                    }
                });
                listBox.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        // keyCode: ^M = 109, NUM CR = 16777296
                        if (e.character == '\r')
                            accept();
                    }
                });

                // add entries
                for (Entry<K, ?> entry : candidates.entrySet()) {
                    final K key = entry.getKey();
                    Object value = entry.getValue();
                    boolean selected = Objects.equals(key, initial);
                    int insertIndex = listBox.getItemCount();
                    listBox.add(String.valueOf(value));
                    if (selected)
                        listBox.select(insertIndex);
                }

                return new Ref<Set<K>>() {
                    @Override
                    public Set<K> get() {
                        Set<K> set = new HashSet<K>(size);
                        for (int i = 0; i < keys.length; i++)
                            if (listBox.isSelected(i))
                                set.add(keys[i].key);
                        return set;
                    }

                    @Override
                    public void set(Object value) {
                        throw new ReadOnlyException();
                    }
                };
            }
        };
        try {
            @SuppressWarnings("unchecked")
            Set<K> selection = (Set<K>) dialog.open();
            assert selection != null;
            return selection;
        } catch (CancelException e) {
            return null;
        }
    }

}
