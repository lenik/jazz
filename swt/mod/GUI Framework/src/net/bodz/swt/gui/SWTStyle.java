package net.bodz.swt.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.bodz.bas.gui.Interaction;
import net.bodz.bas.gui.RenderException;
import net.bodz.bas.gui.Renderer;
import net.bodz.bas.gui.Style;
import net.bodz.bas.gui.a.Border;
import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.ref.Var;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.Ns;
import net.bodz.swt.controls.helper.DynamicControl;
import net.bodz.swt.gui.a.MaxLength;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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
    }

    abstract class _R_Object extends SWTRenderer {

        @Override
        protected Control render(GUIVar<Object> ctxVar, Composite parent,
                int style) throws RenderException, SWTException {
            GUIVarMeta meta = ctxVar.getMeta();
            Control control = null;
            try {
                if (meta.isReadOnly()) {
                    Object value = ctxVar.get();
                    if (value == null) {
                        Label label = new Label(parent, style);
                        label.setText("(null)");
                        return control = label;
                    } else {
                        return control = renderObject(ctxVar, parent, style);
                    }
                }
                DynamicControl dyna = new DynamicControl(parent, SWT.NONE);
                control = renderObject(ctxVar, dyna, style);
                return dyna;
            } finally {
                if (control != null)
                    addEffects(control, ctxVar);
            }
        }

        protected abstract Control renderObject(GUIVar<Object> var,
                Composite parent, int style) throws RenderException,
                SWTException;

    }

    class R_Text extends SWTRenderer {

        @Override
        public Control render(final GUIVar<Object> var, Composite parent,
                int style) throws RenderException, SWTException {
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
                // verify, TODO: add check support
                Class<?> type = meta.getType();
                final TypeParser parser;
                try {
                    parser = TypeParsers.guess(type, true);
                } catch (ParseException e) {
                    throw new RenderException(
                            "Can't guess parser for number class: " + type);
                }
                text.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        try {
                            Object val = parser.parse(text.getText());
                            var.set(val);
                        } catch (ParseException err) {
                            interact(text).alert("Parse Failure", err);
                            text.setSelection(0, text.getText().length());
                            text.setFocus();
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
        protected Control render(GUIVar<Object> var, Composite parent, int style)
                throws RenderException, SWTException {
            // GUIVarMeta meta = var.getMeta();
            // if min/max then render in slider...
            return rText.render(var, parent, style);
        }

    }

    class R_Boolean extends SWTRenderer {

        @Override
        protected Control render(final GUIVar<Object> var,
                final Composite parent, int style) throws RenderException,
                SWTException {
            GUIVarMeta meta = var.getMeta();
            boolean val = (Boolean) var.get();
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
                        check.setSelection((Boolean) var.get());
                    }
                });
            // verify
            if (!meta.isReadOnly()) {
                // checkers.
            }
            addEffects(check, var);
            return check;
        }

    }

}
