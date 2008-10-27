package net.bodz.swt.gui;

import net.bodz.bas.gui.RenderException;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SWTFormStyle extends SWTStyle {

    private static final long serialVersionUID = -6334086901913739452L;

    protected static class FormConfig extends Config {

        public int margin = 3;

        public static FormConfig getDefault() {
            return instance;
        }

        private static FormConfig instance = new FormConfig();

    }

    protected final FormConfig config;

    public SWTFormStyle(FormConfig config) {
        this.config = config;
    }

    public SWTFormStyle() {
        this(FormConfig.getDefault());
    }

    @Override
    protected void setup() {
        super.setup();
        put(Object.class, new R_Object());
    }

    class R_Object extends _R_Object {

        @Override
        protected Control renderObject(GUIVar<?> var, Composite parent,
                int style) throws RenderException, SWTException {
            assert var != null;
            // GUIVarMeta meta = var.getMeta();
            return null;
        }

    }

}
