package net.bodz.bas.t.tree;

/**
 * <pre>
 * &&│ z 0 1 e
 * ──┼────────
 * z │ z z z z
 * 0 │ z 0 z 0
 * 1 │ z z 1 1
 * e │ z 0 1 e
 *            
 * OR│ z 0 1 e
 * ──┼────────
 * z │ z 0 1 e
 * 0 │ 0 0 e e
 * 1 │ 1 e 1 e
 * e │ e e e e
 * 
 * &&│ 0 1
 * ──┼────
 * 0 │ 0 z
 * 1 │ z 1
 *       
 * OR│ 0 1
 * ──┼────
 * 0 │ 0 e
 * 1 │ e 1
 * 
 * &&│ z e
 * ──┼────
 * z │ z z
 * e │ z e
 *        
 * OR│ z e
 * ──┼────
 * z │ z e
 * e │ e e
 * 
 * &&│ e 1
 * ──┼────
 * 0 │ 0 z
 * z │ z z
 *       
 * OR│ e 1
 * ──┼────
 * 0 │ e e
 * z │ e 1
 * </pre>
 */
public interface IQuadState {

    int Q0 = 0;
    int ZERO = 1;
    int ONE = 2;
    int Q1 = 3;

}
