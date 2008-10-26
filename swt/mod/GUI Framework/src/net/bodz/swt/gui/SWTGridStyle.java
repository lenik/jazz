package net.bodz.swt.gui;

import java.lang.reflect.Modifier;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.swt.gui.GUIStructs.GUIMethodMeta;
import net.bodz.swt.gui.GUIStructs.GUIObjectStruct;
import net.bodz.swt.gui.GUIVars.GUIFieldMeta;
import net.bodz.swt.gui.GUIVars.GUIPropertyMeta;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class SWTGridStyle extends SWTStyle {

    private static final long serialVersionUID = -6476584130668546414L;

    protected static class GridConfig extends Config {

        public int margin = 3;

        public static GridConfig getDefault() {
            return instance;
        }

        private static GridConfig instance = new GridConfig();

    }

    protected final GridConfig config;

    public SWTGridStyle(GridConfig config) {
        this.config = config;
    }

    public SWTGridStyle() {
        this(GridConfig.getDefault());
    }

    @Override
    protected void setup() {
        super.setup();
        put(Object.class, new R_Object());
    }

    private static final int DEFAULT   = 0;
    private static final int PUBLIC    = 1;
    private static final int PROTECTED = 2;
    private static final int PRIVATE   = 3;

    private static int iconIndex(int modifiers) {
        switch (modifiers
                & (Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE)) {
        case Modifier.PUBLIC:
            return PUBLIC;
        case Modifier.PROTECTED:
            return PROTECTED;
        case Modifier.PRIVATE:
            return PRIVATE;
        default:
            return DEFAULT;
        }
    }

    private static String[] fieldIcons;
    private static String[] propertyIcons;
    private static String[] methodIcons;
    static {
        fieldIcons = new String[] {
            //
            "/icons/full/obj16/field_default_obj.gif",
            "/icons/full/obj16/field_public_obj.gif",
            "/icons/full/obj16/field_protected_obj.gif",
            "/icons/full/obj16/field_private_obj.gif", };
        methodIcons = new String[] {
            //
            "/icons/full/obj16/methdef_obj.gif",
            "/icons/full/obj16/methpub_obj.gif",
            "/icons/full/obj16/methpro_obj.gif",
            "/icons/full/obj16/methpri_obj.gif", };
        propertyIcons = methodIcons;
    }

    class R_Object extends _R_Object {

        @Override
        protected Composite renderObject(GUIVar<Object> var, Composite parent,
                int style) throws RenderException, SWTException {
            assert var != null;
            GUIVarMeta meta = var.getMeta();
            Composite grid = new Composite(parent, styleFx(style, var));
            // icon label control
            GridLayout gridLayout = new GridLayout(3, false);
            grid.setLayout(gridLayout);

            // fill
            Class<?> type = meta.getType();
            Object object = var.get();
            if (object != null) {
                GUIStruct objStruct = new GUIObjectStruct(object);
                for (GUIVar<?> iVar : objStruct)
                    renderRow(grid, iVar);
            }
            return grid;
        }

        void renderRow(Composite grid, GUIVar<?> var) throws RenderException,
                SWTException {
            GUIVarMeta meta = var.getMeta();
            String name = meta.getName();
            GUIHint hint = meta.getHint();

            // Column #1
            Label iconLabel = new Label(grid, SWT.NONE);
            try {
                Image icon = hint == null ? null : hint.getIcon();
                if (icon == null) {
                    String iconPath;
                    int modifiers = meta.getModifiers();
                    if (meta instanceof GUIFieldMeta)
                        iconPath = fieldIcons[iconIndex(modifiers)];
                    else if (meta instanceof GUIPropertyMeta)
                        iconPath = propertyIcons[iconIndex(modifiers)];
                    else if (meta instanceof GUIMethodMeta)
                        iconPath = methodIcons[iconIndex(modifiers)];
                    else
                        iconPath = config.defaultIcon;
                    icon = SWTResources.getImageRes(iconPath);
                }
                iconLabel.setImage(icon);
            } catch (CreateException e) {
                throw new RenderException("Failed to render icon", e);
            }

            // Column #2
            Label label = new Label(grid, SWT.NONE);
            String labelString = hint == null ? null : hint.getLabel();
            if (labelString == null)
                labelString = name;
            if (labelString != null)
                label.setText(labelString);

            // Column #3
            Control child = SWTGridStyle.this.render(var, grid, styleFx(
                    SWT.NONE, hint));

            Point iconz = iconLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            Point labelz = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            Point childz = child.computeSize(SWT.DEFAULT, SWT.DEFAULT);

            GridData icond = new GridData(SWT.CENTER, SWT.TOP, false, false);
            GridData labeld = new GridData(SWT.LEFT, SWT.TOP, false, false);
            GridData childd = new GridData(SWT.FILL, SWT.TOP, true, false);

            int labelH = Math.max(iconz.y, labelz.y);
            if (labelH >= childz.y) {
                icond.verticalAlignment = SWT.CENTER;
                labeld.verticalAlignment = SWT.CENTER;
                childd.verticalAlignment = SWT.CENTER;
            }
            iconLabel.setLayoutData(icond);
            label.setLayoutData(labeld);
            child.setLayoutData(childd);

            addEffects(child, var);
        }
    }

}
