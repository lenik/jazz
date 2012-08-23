package net.bodz.swt.viz.style.form;

import net.bodz.swt.viz.style.AbstractVisualization;

public class FormVisualization
        extends AbstractVisualization {

    private static final long serialVersionUID = -6334086901913739452L;

    protected static class FormConfig
            extends Config {

        public int margin = 3;

        public static FormConfig getDefault() {
            return instance;
        }

        private static FormConfig instance = new FormConfig();

    }

    protected final FormConfig config;

    public FormVisualization(FormConfig config) {
        this.config = config;
    }

    public FormVisualization() {
        this(FormConfig.getDefault());
    }

    @Override
    protected void setup() {
        super.setup();
        put(Object.class, new R_Object(this));
    }

}
