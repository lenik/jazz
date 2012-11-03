package net.bodz.swt.gui.dev;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;

import net.bodz.bas.gui.spec0.IStroke;

public abstract class SWTStroke
        implements IStroke, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @see SWT#CAP_ROUND
     * @see SWT#CAP_FLAT
     * @see SWT#CAP_SQUARE
     */
    public abstract int getCap();

    public abstract void setCap(int cap);

    public abstract int[] getDash();

    public abstract void setDash(int[] dash);

    /**
     * @see SWT#JOIN_MITER
     * @see SWT#JOIN_ROUND
     * @see SWT#JOIN_BEVEL
     */
    public abstract int getJoin();

    public abstract void setJoin(int join);

    /**
     * @see SWT#LINE_SOLID
     * @see SWT#LINE_DASH
     * @see SWT#LINE_DOT
     * @see SWT#LINE_DASHDOT
     * @see SWT#LINE_DASHDOTDOT
     * @see SWT#LINE_CUSTOM
     */
    public abstract int getStyle();

    public abstract void setStyle(int style);

    public abstract int getWidth();

    public abstract void setWidth(int width);

    public static class Static
            extends SWTStroke {

        static final long serialVersionUID = 6619194642540979332L;

        public int cap;

        public int[] dash;

        public int join;

        public int style;

        public int width;

        @Override
        public int getCap() {
            return cap;
        }

        @Override
        public void setCap(int cap) {
            this.cap = cap;
        }

        @Override
        public int[] getDash() {
            return dash;
        }

        @Override
        public void setDash(int[] dash) {
            this.dash = dash;
        }

        @Override
        public int getJoin() {
            return join;
        }

        @Override
        public void setJoin(int join) {
            this.join = join;
        }

        @Override
        public int getStyle() {
            return style;
        }

        @Override
        public void setStyle(int style) {
            this.style = style;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public void setWidth(int width) {
            this.width = width;
        }

    }

    public static class Ref
            extends SWTStroke {

        static final long serialVersionUID = 7504085295029360367L;

        GC gc;

        public Ref(GC gc) {
            assert gc != null;
            this.gc = gc;
        }

        @Override
        public int getCap() {
            return gc.getLineCap();
        }

        @Override
        public void setCap(int cap) {
            gc.setLineCap(cap);
        }

        @Override
        public int[] getDash() {
            return gc.getLineDash();
        }

        @Override
        public void setDash(int[] dash) {
            gc.setLineDash(dash);
        }

        @Override
        public int getJoin() {
            return gc.getLineJoin();
        }

        @Override
        public void setJoin(int join) {
            gc.setLineJoin(join);
        }

        @Override
        public int getStyle() {
            return gc.getLineStyle();
        }

        @Override
        public void setStyle(int style) {
            gc.setLineStyle(style);
        }

        @Override
        public int getWidth() {
            return gc.getLineWidth();
        }

        @Override
        public void setWidth(int width) {
            gc.setLineWidth(width);
        }

    }

}
