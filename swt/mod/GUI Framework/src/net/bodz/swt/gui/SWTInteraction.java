package net.bodz.swt.gui;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.gui._Interaction;
import net.bodz.bas.lang.err.CancelException;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.util.Objects;
import net.bodz.swt.controls.helper.StackComposite;
import net.bodz.swt.dialogs.SimpleDialog;
import net.bodz.swt.gui.styles.base.SWTStrategy;
import net.bodz.swt.gui.styles.grid.SWTGridStrategy;
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

public class SWTInteraction extends _Interaction {

    public static final String ERROR       = "error";
    public static final String INFORMATION = "information";
    public static final String QUESTION    = "question";
    public static final String WARNING     = "warning";
    public static final String WORKING     = "working";

    static class Params {
        Object  detail;
        Image   icon;
        boolean cancelable;

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
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

        // key -> icon image res path.
        static final TextMap<String> aliases;
        static {
            aliases = new HashTextMap<String>();
            aliases.put(ERROR, "");
            aliases.put(INFORMATION, "");
            aliases.put(QUESTION, "");
            aliases.put(WARNING, "");
            aliases.put(WORKING, "");
        }

        public void setIcon(String alias) {
            String respath = aliases.get(alias);
            if (respath == null)
                throw new IllegalArgumentException("invalid icon alias: "
                        + alias);
            Image image = SWTResources.getImageRes(respath);
            setIcon(image);
        }
    }

    static Params getParams(Object detail) {
        if (detail instanceof Params)
            return (Params) detail;
        Params params = new Params();
        params.detail = detail;
        return params;
    }

    class IADialog extends SimpleDialog {

        public IADialog(Shell parent, int style, String title) {
            super(parent, style, title);
        }

        @Override
        protected void addEffects() {
            SWTInteraction.this.addEffects(shell);
        }

    }

    protected void addEffects(Shell shell) {
    }

    static SWTGridStrategy strategy = new SWTGridStrategy();

    private final Shell    parent;
    private final int      style;

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SWTInteraction(Shell parent, int style) {
        this.parent = parent;
        this.style = style;
    }

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SWTInteraction(int style) {
        this(new Shell(), style);
    }

    public SWTInteraction(Shell parent) {
        this(parent, SWT.NONE);
    }

    public SWTInteraction() {
        this(SWT.NONE);
    }

    @Override
    public void alert(String title, Object detail) {
        final Params params = getParams(detail);
        SimpleDialog dialog = new IADialog(parent, style, title) {
            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    strategy.render(params.getDetail(), parent, SWT.NONE);
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
    public boolean confirm(String title, Object detail) {
        final Params params = getParams(detail);
        SimpleDialog dialog = new IADialog(parent, style, title) {
            @Override
            protected void createDetail(Composite parent)
                    throws CreateException {
                try {
                    strategy.render(params.getDetail(), parent, SWT.NONE);
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
    public <T> T prompt(String title, Object detail, final Class<T> type,
            final T initial) {
        final Params params = getParams(detail);
        final TypeParser parser;
        try {
            parser = TypeParsers.guess(type, true);
        } catch (ParseException e) {
            throw new IllegalUsageError("non-parsable type: " + type);
        }
        SimpleDialog dialog = new IADialog(parent, style, title) {
            private Text text;

            @Override
            protected void createDetail(Composite parent) throws SWTException,
                    CreateException {
                try {
                    strategy.render(params.getDetail(), parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent) throws SWTException,
                    CreateException {
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
        private SWTStrategy    style;
        private StackComposite stack;
        private Control[]      controls;
        private int            next;

        public PreRenderred(Composite parent, SWTStrategy style, int size) {
            this.stack = new StackComposite(parent, SWT.BORDER);
            this.style = style;
            this.controls = new Control[size];
        }

        public int render(Object value) throws RenderException, SWTException {
            Control control = style.render(value, stack, SWT.NONE);
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
    public <K> K choice(String title, Object detail,
            final Map<K, ?> candidates, final K initial) {
        final Params params = getParams(detail);
        SimpleDialog dialog = new IADialog(parent, style, title) {
            {
                set(initial);
            }

            @Override
            protected void createDetail(Composite parent) throws SWTException,
                    CreateException {
                try {
                    strategy.render(params.getDetail(), parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent) throws SWTException,
                    CreateException {
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
                        strategy.render(value, parent, SWT.NONE);
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
                        parent, strategy, size) : null;
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
        try {
            @SuppressWarnings("unchecked")
            K key = (K) dialog.open();
            return key;
        } catch (CancelException e) {
            return null;
        }
    }

    @Override
    public <K> Set<K> choices(String title, Object detail,
            final Map<K, ?> candidates, K... initial) {
        final Params params = getParams(detail);
        final Set<K> initials = new HashSet<K>(initial.length);

        for (K k : initial)
            initials.add(k);
        SimpleDialog dialog = new IADialog(parent, style, title) {
            Ref<Set<K>> selection;

            @Override
            protected void createDetail(Composite parent) throws SWTException,
                    CreateException {
                try {
                    strategy.render(params.getDetail(), parent, SWT.NONE);
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            }

            @Override
            protected void createBody(Composite parent) throws SWTException,
                    CreateException {
                if (candidates.size() <= FLATMAX) {
                    selection = createChecks(parent, candidates, initials);
                } else
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
                        strategy.render(value, parent, SWT.NONE);
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
                        parent, strategy, size) : null;
                final _K[] keys = new _K[size];
                int index = 0;
                for (Entry<K, ?> entry : candidates.entrySet()) {
                    try {
                        if (hasDetail)
                            preRenderred.render(entry.getValue());
                        keys[index++] = new _K(entry.getKey());
                    } catch (RenderException e) {
                        throw new CreateException(e);
                    }
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
