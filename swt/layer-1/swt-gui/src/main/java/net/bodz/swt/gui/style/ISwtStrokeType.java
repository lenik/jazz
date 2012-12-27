package net.bodz.swt.gui.style;

import org.eclipse.swt.SWT;

public interface ISwtStrokeType {

    /**
     * @see SWT#CAP_ROUND
     * @see SWT#CAP_FLAT
     * @see SWT#CAP_SQUARE
     */
    int getCap();

    void setCap(int cap);

    int[] getDash();

    void setDash(int[] dash);

    /**
     * @see SWT#JOIN_MITER
     * @see SWT#JOIN_ROUND
     * @see SWT#JOIN_BEVEL
     */
    int getJoin();

    void setJoin(int join);

    /**
     * @see SWT#LINE_SOLID
     * @see SWT#LINE_DASH
     * @see SWT#LINE_DOT
     * @see SWT#LINE_DASHDOT
     * @see SWT#LINE_DASHDOTDOT
     * @see SWT#LINE_CUSTOM
     */
    int getStyle();

    void setStyle(int style);

    int getWidth();

    void setWidth(int width);

}
