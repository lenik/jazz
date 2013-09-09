package net.bodz.swt.c.file;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileSelector {

    private boolean selectDirectory = false;
    private int fileStyle = SWT.OPEN;
    private int directoryStyle = SWT.NONE;

    private String dialogTitle;
    private String dialogMessage;
    private boolean dialogOverwrite;
    private String[] filterNames;
    private String[] filterExtensions;
    private int filterIndex = -1;

    public boolean isSelectDirectory() {
        return selectDirectory;
    }

    /**
     * @see SWT#OPEN
     * @see SWT#SAVE
     * @see SWT#MULTI
     */
    public void setFileMode(int fileStyle) {
        selectDirectory = false;
        this.fileStyle = fileStyle;
    }

    public void setDirectoryMode(int directoryStyle) {
        selectDirectory = true;
        this.directoryStyle = directoryStyle;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    public boolean isDialogOverwrite() {
        return dialogOverwrite;
    }

    public void setDialogOverwrite(boolean dialogOverwrite) {
        this.dialogOverwrite = dialogOverwrite;
    }

    public String[] getFilterNames() {
        return filterNames;
    }

    public void setFilterNames(String[] filterNames) {
        this.filterNames = filterNames;
    }

    public String[] getFilterExtensions() {
        return filterExtensions;
    }

    public void setFilterExtensions(String[] filterExtensions) {
        this.filterExtensions = filterExtensions;
    }

    public int getFilterIndex() {
        return filterIndex;
    }

    public void setFilterIndex(int filterIndex) {
        this.filterIndex = filterIndex;
    }

    public String select(Shell shell, String orig) {
        String path;
        if (selectDirectory) {
            DirectoryDialog dialog = new DirectoryDialog(shell, directoryStyle);
            if (orig != null)
                dialog.setFilterPath(orig);
            if (dialogTitle != null)
                dialog.setText(dialogTitle);
            if (dialogMessage != null)
                dialog.setMessage(dialogMessage);
            path = dialog.open();
        } else {
            FileDialog dialog = new FileDialog(shell, fileStyle);
            if (orig != null)
                dialog.setFileName(orig);
            if (dialogTitle != null)
                dialog.setText(dialogTitle);
            if (filterNames != null)
                dialog.setFilterNames(filterNames);
            if (filterExtensions != null)
                dialog.setFilterExtensions(filterExtensions);
            if (filterIndex != -1)
                dialog.setFilterIndex(filterIndex);
            dialog.setOverwrite(dialogOverwrite);
            path = dialog.open();
        }
        return path;
    }

}
