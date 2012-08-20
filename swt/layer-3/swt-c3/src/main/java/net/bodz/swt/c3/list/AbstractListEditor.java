package net.bodz.swt.c3.list;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import net.bodz.bas.err.CorruptedStateError;

public abstract class AbstractListEditor<T>
        extends Composite {

    private Label label;
    private java.util.List<T> list;
    private List listBox;

    private int dragIndex = -1;

    class ArrangeAdapter
            extends MouseAdapter
            implements MouseMoveListener {

        int downIndex = -1;

        @Override
        public void mouseDown(MouseEvent e) {
            dragIndex = listBox.getSelectionIndex();
            downIndex = dragIndex;
        }

        @Override
        public void mouseUp(MouseEvent e) {
            mouseMove(e);
            if (dragIndex != -1) {
                int dropIndex = listBox.getSelectionIndex();
                assert dropIndex != -1;
                if (dragIndex != dropIndex) {
                    T dragValue = list.remove(dragIndex);
                    list.add(dropIndex, dragValue);
                }
                dragIndex = -1;
                downIndex = -1;
            }
        }

        @Override
        public void mouseMove(MouseEvent e) {
            if ((e.stateMask & SWT.CTRL) != 0)
                return;
            if (downIndex != -1) {
                int overIndex = listBox.getSelectionIndex();
                assert overIndex != -1;
                if (overIndex != downIndex) {
                    String downItem = listBox.getItem(downIndex);
                    listBox.remove(downIndex);
                    listBox.add(downItem, overIndex);
                    downIndex = overIndex;
                    listBox.setSelection(downIndex);
                }
            }
        }

    }

    private boolean allowArrange;
    private ArrangeAdapter arrangeAdapter = new ArrangeAdapter();

    public AbstractListEditor(Composite parent, int style) {
        super(parent, style);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.horizontalSpacing = 0;
        gridLayout.marginHeight = 0;
        setLayout(gridLayout);

        label = new Label(this, SWT.NONE);
        final GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, false, false);
        gd_label.widthHint = 116;
        label.setLayoutData(gd_label);

        listBox = new List(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        final GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true);
        listBox.setLayoutData(gd_list);
        listBox.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                fireSelection(e);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                fireDefaultSelection(e);
            }
        });

        buttons = new Composite(this, SWT.NONE);
        buttons.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        final GridLayout buttonsLayout = new GridLayout();
        buttonsLayout.marginWidth = 0;
        buttonsLayout.marginHeight = 0;
        buttonsLayout.numColumns = 2;
        buttons.setLayout(buttonsLayout);

        addButton = new Button(buttons, SWT.NONE);
        addButton.setText("+");
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (list == null)
                    list = new ArrayList<T>();
                T o = createObject();
                if (o == null) // new created object must be non-null.
                    return;
                String display = format(o);
                list.add(o);
                listBox.add(display);
            }
        });

        removeButton = new Button(buttons, SWT.NONE);
        removeButton.setText("-");
        removeButton.addSelectionListener(new SelectionAdapter() {
            final boolean automove = true; // auto move to next item after the removed one.

            @Override
            public void widgetSelected(SelectionEvent e) {
                assert list != null;
                int index = listBox.getSelectionIndex();
                if (index != -1) {
                    if (index >= list.size() || index >= listBox.getItemCount())
                        throw new CorruptedStateError("Editor contents corrupted");
                    list.remove(index);
                    listBox.remove(index);
                    if (automove)
                        if (index < list.size())
                            listBox.setSelection(index);
                        else if (list.size() > 0)
                            listBox.setSelection(list.size() - 1);
                }
            }
        });
    }

    public String getText() {
        return label.getText();
    }

    public void setText(String text) {
        label.setText(text);
    }

    public java.util.List<T> getList() {
        return list;
    }

    public void setList(java.util.List<T> list) {
        this.list = list;
        listBox.removeAll();
        if (list != null) {
            for (T o : list) {
                String disp = format(o);
                listBox.add(disp);
            }
            // if (!list.isEmpty())
            // listBox.setSelection(0);
        }
    }

    public boolean isAllowArrange() {
        return allowArrange;
    }

    public void setAllowArrange(boolean allow) {
        if (this.allowArrange != allow) {
            if (allow) {
                listBox.addMouseListener(arrangeAdapter);
                listBox.addMouseMoveListener(arrangeAdapter);
            } else {
                listBox.removeMouseListener(arrangeAdapter);
                listBox.removeMouseMoveListener(arrangeAdapter);
            }
            this.allowArrange = allow;
        }
    }

    protected String format(T value) {
        if (value == null)
            return "<empty>";
        return String.valueOf(value);
    }

    /**
     * @return <code>null</code> if canceled.
     */
    protected abstract T createObject();

    public T getSelection() {
        int index = listBox.getSelectionIndex();
        if (index == -1)
            return null;
        return list.get(index);
    }

    public String getSelectionText() {
        int index = listBox.getSelectionIndex();
        if (index == -1)
            return null;
        return listBox.getItem(index);
    }

    public int getSelectionIndex() {
        return listBox.getSelectionIndex();
    }

    public void setSelection(int index) {
        listBox.setSelection(index);
    }

    boolean fullStyle;

    @Override
    public void setForeground(Color color) {
        listBox.setForeground(color);
        if (fullStyle) {
            super.setForeground(color);
            label.setForeground(color);
            buttons.setForeground(color);
            addButton.setForeground(color);
            removeButton.setForeground(color);
        }
    }

    @Override
    public void setBackground(Color color) {
        listBox.setBackground(color);
        if (fullStyle) {
            super.setBackground(color);
            label.setBackground(color);
            buttons.setBackground(color);
            addButton.setBackground(color);
            removeButton.setBackground(color);
        }
    }

    @Override
    public void setFont(Font font) {
        listBox.setFont(font);
        if (fullStyle) {
            super.setFont(font);
            label.setFont(font);
            buttons.setFont(font);
            addButton.setFont(font);
            removeButton.setFont(font);
        }
    }

    private ArrayList<SelectionListener> selectionListeners;
    private Button addButton;
    private Composite buttons;
    private Button removeButton;

    public void addSelectionListener(SelectionListener l) {
        if (selectionListeners == null)
            selectionListeners = new ArrayList<SelectionListener>(1);
        selectionListeners.add(l);
    }

    public void removeSelectionListener(SelectionListener l) {
        if (selectionListeners != null)
            selectionListeners.remove(l);
    }

    protected void fireSelection(SelectionEvent e) {
        if (selectionListeners != null)
            for (SelectionListener l : selectionListeners)
                l.widgetSelected(e);
    }

    protected void fireDefaultSelection(SelectionEvent e) {
        if (selectionListeners != null)
            for (SelectionListener l : selectionListeners)
                l.widgetDefaultSelected(e);
    }

}
