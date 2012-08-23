package net.bodz.swt.gui.ia;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.c.string.StringSearch;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.CancelException;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.ia.AbstractUserInteraction;
import net.bodz.bas.gui.ia.IProposal;
import net.bodz.bas.gui.viz.IVisualization;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.lang.fn.Func0;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.Nullables;
import net.bodz.swt.c.composite.StackComposite;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.gui.c3.dialog.SimpleDialog;
import net.bodz.swt.gui.viz.IAction;
import net.bodz.swt.gui.viz.SWTRenderContext;
import net.bodz.swt.gui.viz.style.AbstractVisualization;
import net.bodz.swt.gui.viz.style.grid.GridVisualization;

public class DialogInteraction
        extends AbstractUserInteraction {

    static IVisualization visualization = new GridVisualization();

    private final Shell parent;
    private final int style;

    private Image icon;
    private boolean cancelable;

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

    public static final String ERROR = "error";
    public static final String INFORMATION = "information";
    public static final String QUESTION = "question";
    public static final String WARNING = "warning";
    public static final String WORKING = "working";

    // key -> icon image res path.
    static final Map<String, String> aliases;
    static {
        aliases = new HashMap<String, String>();
        aliases.put(ERROR, "");
        aliases.put(INFORMATION, "");
        aliases.put(QUESTION, "");
        aliases.put(WARNING, "");
        aliases.put(WORKING, "");
    }

    public void setIcon(String alias) {
        String respath = aliases.get(alias);
        if (respath == null)
            throw new IllegalArgumentException(GUINLS.getString("SWTInteraction.badicon") + alias);
        Image image = SWTResources.getImageRes(respath);
        setIcon(image);
    }

    abstract class _Dialog
            extends SimpleDialog {

        class RC
                extends SWTRenderContext {
            @Override
            public void addAction(final IAction action) {
                if (action == null)
                    throw new NullPointerException("action");
                Composite userBar = getUserBar();
                if (userBar == null)
                    throw new IllegalStateException("no user bar");
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

        public SWTRenderContext getRenderContext() {
            return new RC();
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

    }

    protected void addEffects(Shell shell) {
    }

    @Override
    public void alert(String title, final Object detail) {
        class AlertDialog
                extends _Dialog {

            public AlertDialog(Shell parent, int style, String title) {
                super(parent, style, title);
            }

            @Override
            public Object open() {
                return super.open(false);
            }

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                SWTRenderContext rc = getRenderContext();
                try {
                    visualization.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createButtons(Composite parent)
                    throws SWTException, CreateException {
                addOKButton(parent);
            }
        }
        new AlertDialog(parent, style, title).open();
    }

    @Override
    public boolean confirm(String title, final Object detail) {
        class ConfirmDialog
                extends _Dialog {

            SWTRenderContext rc = getRenderContext();

            public ConfirmDialog(Shell parent, int style, String title) {
                super(parent, style, title);
            }

            @Override
            public Boolean open() {
                return (Boolean) super.open(false);
            }

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    visualization.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createButtons(Composite parent)
                    throws SWTException, CreateException {
                addYesButton(parent);
                addNoButton(parent);
            }
        }
        return new ConfirmDialog(parent, style, title).open();
    }

    @Override
    public int ask(String title, final Object detail, final IProposal... proposals) {
        class AskDialog
                extends _Dialog {

            SWTRenderContext rc = getRenderContext();

            public AskDialog(Shell parent, int style, String title) {
                super(parent, style, title);
            }

            @Override
            public Integer open() {
                return (Integer) super.open(false);
            }

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    visualization.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createButtons(Composite parent)
                    throws SWTException, CreateException {
                for (int i = 0; i < proposals.length; i++) {
                    IProposal p = proposals[i];
                    Image image = null;
                    // if (IProposal instanceof GUIProposal) ...
                    String name = p.getName();
                    // if (bundle.contains(name)) ... else
                    String text = Strings.ucfirstWords(name);
                    char c = p.getMnemonic();
                    int m = StringSearch.indexOfIgnoreCase(text, c);
                    if (m != -1)
                        text = text.substring(0, m) + "&" + text.substring(m);
                    Button button = addButton(parent, SWT.NONE, image, text, i);
                    String description = p.getDescription();
                    if (description != null)
                        button.setToolTipText(description);
                    if (i == 0)
                        button.setSelection(true);
                }
            }
        }
        Integer index = new AskDialog(parent, style, title).open();
        if (index == null)
            return -1;
        return index.intValue();
    }

    @Override
    public <T> T prompt(String title, final Object detail, final Class<T> type, final T initial) {

        final IParser<?> parser = Traits.getTrait(type, IParser.class);

        class PromptDialog
                extends _Dialog {

            private Text text;

            public PromptDialog(Shell parent, int style, String title) {
                super(parent, style, title);
            }

            @SuppressWarnings("unchecked")
            @Override
            public T open() {
                return (T) super.open(false);
            }

            @Override
            protected Object evaluate()
                    throws ParseException {
                String s = text.getText();
                return parser.parse(s);
            }

            @Override
            protected void createDetail(Composite parent)
                    throws SWTException, CreateException {
                SWTRenderContext rc = getRenderContext();
                try {
                    visualization.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent)
                    throws CreateException {
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

        }
        return new PromptDialog(parent, style, title).open();
    }

    private static int FLATMAX = 3;

    static class PreRenderred {
        private final SWTRenderContext rc;
        private final AbstractVisualization style;
        private final StackComposite stack;
        private final Control[] controls;
        private int next;

        public PreRenderred(SWTRenderContext rc, Composite parent, AbstractVisualization style, int size) {
            this.rc = rc;
            this.stack = new StackComposite(parent, SWT.BORDER);
            this.style = style;
            this.controls = new Control[size];
        }

        public int render(Object value)
                throws RenderException, SWTException {
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
    public <K> K choice(String title, final Object detail, final Map<K, ?> candidates, final K initial) {
        class ChoiceDialog
                extends _Dialog {

            SWTRenderContext rc = getRenderContext();

            class SetResultByData
                    extends SelectionAdapter {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    Object data = e.widget.getData();
                    result = data;
                }

            }

            Object result;
            SetResultByData setResultByData = new SetResultByData();

            public ChoiceDialog(Shell parent, int style, String title) {
                super(parent, style, title);
            }

            @SuppressWarnings("unchecked")
            @Override
            public K open() {
                return (K) super.open(false);
            }

            @Override
            protected Object evaluate()
                    throws Exception {
                return result;
            }

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    visualization.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent)
                    throws CreateException {
                if (candidates.size() <= FLATMAX)
                    createRadios(parent, candidates, initial);
                else
                    createCombo(parent, candidates, initial);
            }

            void createRadios(Composite parent, Map<?, ?> candidates, Object initial)
                    throws CreateException {
                parent.setLayout(new GridLayout(2, false));
                for (Entry<?, ?> entry : candidates.entrySet()) {
                    final Object key = entry.getKey();
                    Object value = entry.getValue();
                    boolean selected = Nullables.equals(key, initial);
                    Button radio = new Button(parent, SWT.RADIO);
                    radio.addSelectionListener(setResultByData);
                    radio.setSelection(selected);
                    try {
                        visualization.render(rc, value, parent, SWT.NONE);
                    } catch (RenderException e) {
                        throw new CreateException(e);
                    }
                }
            }

            void createCombo(Composite parent, Map<?, ?> candidates, final Object initial)
                    throws CreateException {
                FillLayout fillLayout = new FillLayout();
                fillLayout.type = SWT.VERTICAL;
                parent.setLayout(fillLayout);

                final Combo combo = new Combo(parent, SWT.READ_ONLY);
                combo.setFocus();

                int size = candidates.size();
                final Object[] keys = new Object[size];
                boolean hasDetail = hasDetail(candidates.values());
                final PreRenderred preRenderred = hasDetail ? new PreRenderred(rc, parent, visualization, size) : null;
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
                            result = keys[index];
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
                    boolean selected = Nullables.equals(key, initial);
                    int insertIndex = combo.getItemCount();
                    combo.add(String.valueOf(value));
                    if (selected)
                        combo.select(insertIndex);
                }
            }
        }
        ;
        return new ChoiceDialog(parent, style, title).open();
    }

    @Override
    public <K> Set<K> choices(String title, final Object detail, final Map<K, ?> candidates,
            @SuppressWarnings("unchecked") K... initial) {
        final Set<K> initials = new HashSet<K>(initial.length);
        for (K k : initial)
            initials.add(k);

        class ChoicesDialog
                extends _Dialog {
            SWTRenderContext rc = getRenderContext();
            Func0<Set<K>> selection;

            public ChoicesDialog(Shell parent, int style, String title) {
                super(parent, style, title);
            }

            @SuppressWarnings("unchecked")
            @Override
            public Set<K> open() {
                return (Set<K>) super.open(false);
            }

            @Override
            protected Set<K> evaluate()
                    throws Exception {
                return selection.eval();
            }

            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    visualization.render(rc, detail, parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent)
                    throws CreateException {
                if (candidates.size() <= FLATMAX)
                    selection = createChecks(parent, candidates, initials);
                else
                    selection = createList(parent, candidates, initials);
            }

            Func0<Set<K>> createChecks(Composite parent, Map<K, ?> candidates, Set<K> initial)
                    throws CreateException {
                parent.setLayout(new GridLayout(2, false));

                class KeyButton {
                    K key;
                    Button button;

                    public KeyButton(K key, Button button) {
                        this.key = key;
                        this.button = button;
                    }
                }
                final int size = candidates.size();
                final KeyButton[] keyButtons = new KeyButton[size];
                int index = 0;
                for (Entry<K, ?> entry : candidates.entrySet()) {
                    final K key = entry.getKey();
                    Object value = entry.getValue();
                    boolean selected = initial.contains(value);
                    final Button button = new Button(parent, SWT.CHECK);
                    button.setSelection(selected);
                    keyButtons[index++] = new KeyButton(key, button);
                    try {
                        visualization.render(rc, value, parent, SWT.NONE);
                    } catch (RenderException e) {
                        throw new CreateException(e);
                    }
                }
                return new Func0<Set<K>>() {
                    @Override
                    public Set<K> eval() {
                        Set<K> set = new HashSet<K>(size);
                        for (KeyButton kb : keyButtons)
                            if (kb.button.getSelection())
                                set.add(kb.key);
                        return set;
                    }
                };
            }

            Func0<Set<K>> createList(Composite parent, Map<K, ?> candidates, Set<K> initial)
                    throws CreateException {
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
                final PreRenderred preRenderred = hasDetail ? new PreRenderred(rc, parent, visualization, size) : null;
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
                    boolean selected = Nullables.equals(key, initial);
                    int insertIndex = listBox.getItemCount();
                    listBox.add(String.valueOf(value));
                    if (selected)
                        listBox.select(insertIndex);
                }
                return new Func0<Set<K>>() {
                    @Override
                    public Set<K> eval() {
                        Set<K> set = new HashSet<K>(size);
                        for (int i = 0; i < keys.length; i++)
                            if (listBox.isSelected(i))
                                set.add(keys[i].key);
                        return set;
                    }
                };
            }
        }
        return new ChoicesDialog(parent, style, title).open();
    }

}
