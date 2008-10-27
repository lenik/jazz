package net.bodz.swt.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.EventObject;

import net.bodz.bas.gui.Interaction;
import net.bodz.bas.gui.RenderException;
import net.bodz.bas.gui.Renderer;
import net.bodz.bas.gui.Style;
import net.bodz.bas.gui.a.Border;
import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.ref.Var;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.Ns;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.adapters.CommitAdapter;
import net.bodz.swt.adapters.CommitException;
import net.bodz.swt.controls.helper.DynamicControl;
import net.bodz.swt.gui.a.MaxLength;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

public abstract class SWTStyle extends Style {

    private static final long serialVersionUID = 4665944902525510516L;

    protected static class Config {
        public String defaultIcon     = "/icons/full/obj16/genericvariable_obj.gif";
        public int    defaultIconSize = 16;

        public static Config getDefault() {
            return instance;
        }

        private static Config instance = new Config();

    }

    public SWTStyle() {
        setup();
    }

    @Override
    public SWTRenderer put(Class<?> key, Renderer value) {
        if (!(value instanceof SWTRenderer))
            throw new IllegalArgumentException("not a SWTRenderer: " + value);
        return (SWTRenderer) super.put(key, value);
    }

    @Override
    public Control render(Var<?> var) throws RenderException {
        try {
            return render((GUIVar<?>) var, null, SWT.NONE);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        SWTRenderer renderer = findRenderer(var);
        if (renderer == null)
            throw new RenderException("Don't know how to render "
                    + var.getMeta().getType());
        @SuppressWarnings("unchecked")
        GUIVar<Object> gvar = (GUIVar<Object>) var;
        return renderer.render(gvar, parent, style);
    }

    @Override
    protected SWTRenderer findRenderer(Var<?> var) {
        return (SWTRenderer) super.findRenderer(var);
    }

    protected void addEffects(Control control, GUIVar<?> var) {
        GUIVarMeta meta = var.getMeta();
        GUIHint hint = meta.getHint();
        if (hint == null)
            return;
        Device device = control.getDisplay();
        if (hint.doc != null)
            control.setToolTipText(hint.doc);
        if (hint.visible != null)
            control.setVisible(hint.visible);
        if (hint.enabled != null)
            control.setEnabled(hint.enabled);
        if (hint.color != null)
            control.setForeground(new Color(device, hint.color));
        if (hint.backColor != null)
            control.setBackground(new Color(device, hint.backColor));
        Font font = hint.getFont(device);
        if (font != null)
            control.setFont(font);
        if (hint.preferredSize != null)
            control.setSize(hint.preferredSize);
    }

    protected Interaction interact(Control active) {
        return new SWTInteraction(active.getShell());
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        put(String.class, new R_Text());
        put(Number.class, new R_Number());
        put(boolean.class, new R_Boolean());
        put(Boolean.class, new R_Boolean());
        put(File.class, new R_File());
        // put(byte[].class, new R_binary);
    }

    abstract class _R_Object extends SWTRenderer {

        @Override
        protected Control render(GUIVar<?> var, Composite parent, int style)
                throws RenderException, SWTException {
            GUIVarMeta meta = var.getMeta();
            Control control = null;
            try {
                if (meta.isReadOnly()) {
                    Object value = var.get();
                    if (value == null) {
                        Label label = new Label(parent, style);
                        label.setText("(null)");
                        return control = label;
                    } else {
                        return control = renderObject(var, parent, style);
                    }
                }
                DynamicControl dyna = new DynamicControl(parent, SWT.NONE);
                control = renderObject(var, dyna, style);
                return dyna;
            } finally {
                if (control != null)
                    addEffects(control, var);
            }
        }

        protected abstract Control renderObject(GUIVar<?> var,
                Composite parent, int style) throws RenderException,
                SWTException;

    }

    class R_Text extends SWTRenderer {

        @Override
        public Control render(final GUIVar<?> var, Composite parent, int style)
                throws RenderException, SWTException {
            GUIVarMeta meta = var.getMeta();
            boolean readOnly = meta.isReadOnly();
            String val = String.valueOf(var.get());
            if (readOnly) {
                final Label label = new Label(parent, style);
                label.setText(val);
                if (meta.hasPropertyChangeSupport())
                    bindProperty(var, label, new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            label.setText(String.valueOf(var.get()));
                        }
                    });
                return label;
            } else {
                Integer border = (Integer) Ns.getValue(meta, Border.class);
                if (border == null || (int) border > 0)
                    style |= SWT.BORDER;
                final Text text = new Text(parent, style);
                // Ns.getValue(meta, EchoChar.class);
                // text.setEchoChar(echo);
                MaxLength maxLength = meta.getAnnotation(MaxLength.class);
                if (maxLength != null) {
                    int len = maxLength.value();
                    text.setTextLimit(len);
                }
                text.setText(val);
                if (meta.hasPropertyChangeSupport())
                    bindProperty(var, text, new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            text.setText(String.valueOf(var.get()));
                        }
                    });
                Class<?> type = meta.getType();
                final TypeParser parser;
                try {
                    parser = TypeParsers.guess(type, true);
                } catch (ParseException e) {
                    throw new RenderException(
                            "Can't guess parser for number class: " + type);
                }
                ControlAdapters.commit(text, new CommitAdapter(interact(text)) {
                    @Override
                    public void commit(EventObject event)
                            throws CommitException {
                        try {
                            Object val = parser.parse(text.getText());
                            var.check(val);
                            var.set(val);
                        } catch (ParseException e) {
                            throw new CommitException(e);
                        } catch (CheckException e) {
                            throw new CommitException(e);
                        }
                    }
                });
                return text;
            }
        }

    }

    class R_Number extends SWTRenderer {

        private final R_Text rText = new R_Text();

        @Override
        protected Control render(GUIVar<?> var, Composite parent, int style)
                throws RenderException, SWTException {
            // GUIVarMeta meta = var.getMeta();
            // if min/max then render in slider...
            return rText.render(var, parent, style);
        }

    }

    class R_Boolean extends SWTRenderer {

        @Override
        protected Control render(final GUIVar<?> var, final Composite parent,
                int style) throws RenderException, SWTException {
            GUIVarMeta meta = var.getMeta();
            Boolean _val = (Boolean) var.get();
            boolean val = _val == null ? false : _val;
            final Button check = new Button(parent, style | SWT.CHECK);
            check.setSelection(val);
            if (meta.isReadOnly())
                check.setEnabled(false);
            else {
                check.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        var.set(check.getSelection());
                    }
                });
            }
            if (meta.hasPropertyChangeSupport())
                bindProperty(var, check, new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        Boolean _val = (Boolean) var.get();
                        check.setSelection(_val == null ? false : _val);
                    }
                });
            if (!meta.isReadOnly()) {
                ControlAdapters.commit(check,
                        new CommitAdapter(interact(check)) {
                            @Override
                            public void commit(EventObject event)
                                    throws CommitException {
                                boolean val = check.getSelection();
                                try {
                                    var.check(val);
                                    var.set(val);
                                } catch (CheckException e) {
                                    throw new CommitException(e);
                                }
                            }
                        });
            }
            addEffects(check, var);
            return check;
        }
    }

    class R_File extends SWTRenderer {

        @Override
        protected Control render(final GUIVar<?> var, final Composite parent,
                int style) throws RenderException, SWTException {
            GUIVarMeta meta = var.getMeta();
            File val = (File) var.get();
            assert val != null;
            final Composite comp = new Composite(parent, style);
            BorderLayout layout = new BorderLayout();
            comp.setLayout(layout);
            final Text fileText = new Text(comp, SWT.BORDER);
            fileText.setText(val.getPath());
            fileText.setLayoutData(BorderLayout.CENTER);
            final Button browseButton = new Button(comp, SWT.NONE);
            browseButton.setText("Browse");
            browseButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    FileDialog dialog = new FileDialog(parent.getShell());
                    String path = dialog.open();
                    if (path != null)
                        fileText.setText(path);
                }
            });
            browseButton.setLayoutData(BorderLayout.EAST);
            if (meta.isReadOnly()) {
                fileText.setEnabled(false);
                browseButton.setEnabled(false);
            } else {
                ControlAdapters.commit(fileText, new CommitAdapter(//
                        interact(fileText)) {
                    @Override
                    public void commit(EventObject event)
                            throws CommitException {
                        File file = new File(fileText.getText());
                        try {
                            var.check(file);
                            var.set(file);
                        } catch (CheckException e) {
                            throw new CommitException(e);
                        }
                    }
                });
            }
            if (meta.hasPropertyChangeSupport())
                bindProperty(var, fileText, new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        File file = (File) var.get();
                        assert file != null;
                        fileText.setText(String.valueOf(file));
                    }
                });
            addEffects(comp, var);
            return comp;
        }
    }

}
