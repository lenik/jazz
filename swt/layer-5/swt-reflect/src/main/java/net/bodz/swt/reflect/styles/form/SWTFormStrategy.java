package net.bodz.swt.reflect.styles.form;

import net.bodz.swt.gui.styles.base.SWTStrategy;

public class SWTFormStrategy extends SWTStrategy {

    private static final long serialVersionUID = -6334086901913739452L;

    protected static class FormConfig extends Config {

        public int margin = 3;

        public static FormConfig getDefault() {
            return instance;
        }

        private static FormConfig instance = new FormConfig();

    }

    protected final FormConfig config;

    public SWTFormStrategy(FormConfig config) {
        this.config = config;
    }

    public SWTFormStrategy() {
        this(FormConfig.getDefault());
    }

    @Override
    protected void setup() {
        super.setup();
        put(Object.class, new R_Object(this));
    }

}
