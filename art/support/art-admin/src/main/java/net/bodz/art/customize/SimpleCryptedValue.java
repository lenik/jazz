package net.bodz.art.customize;

import java.io.UnsupportedEncodingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SimpleCryptedValue {

    private Text txtEncrypted;
    private Text txtPlain;
    protected Shell shell;

    public static void main(String[] args) {
        try {
            SimpleCryptedValue window = new SimpleCryptedValue();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        final Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setLayout(new FormLayout());
        shell.setSize(500, 150);
        shell.setText("Simple Encrypted Value Look");

        final Label label = new Label(shell, SWT.NONE);
        final FormData formData = new FormData();
        formData.bottom = new FormAttachment(0, 45);
        formData.right = new FormAttachment(0, 110);
        formData.top = new FormAttachment(0, 25);
        formData.left = new FormAttachment(0, 5);
        label.setLayoutData(formData);
        label.setText("&Plain Text");

        txtPlain = new Text(shell, SWT.BORDER);
        final FormData formData_1 = new FormData();
        formData_1.bottom = new FormAttachment(label, 0, SWT.BOTTOM);
        formData_1.right = new FormAttachment(100, -5);
        formData_1.top = new FormAttachment(label, 0, SWT.TOP);
        formData_1.left = new FormAttachment(label, 5, SWT.RIGHT);
        txtPlain.setLayoutData(formData_1);

        final Label label_1 = new Label(shell, SWT.NONE);
        final FormData formData_2 = new FormData();
        formData_2.bottom = new FormAttachment(label, 25, SWT.BOTTOM);
        formData_2.top = new FormAttachment(label, 5, SWT.BOTTOM);
        formData_2.right = new FormAttachment(label, 105, SWT.LEFT);
        formData_2.left = new FormAttachment(label, 0, SWT.LEFT);
        label_1.setLayoutData(formData_2);
        label_1.setText("E&ncrypted Text");

        txtEncrypted = new Text(shell, SWT.BORDER);
        final FormData formData_1_1 = new FormData();
        formData_1_1.bottom = new FormAttachment(label_1, 20, SWT.TOP);
        formData_1_1.top = new FormAttachment(label_1, 0, SWT.TOP);
        formData_1_1.right = new FormAttachment(100, -5);
        formData_1_1.left = new FormAttachment(label_1, 110, SWT.LEFT);
        txtEncrypted.setLayoutData(formData_1_1);

        final Button btnEncrypt = new Button(shell, SWT.NONE);
        btnEncrypt.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                encrypt();
            }
        });
        final FormData formData_3 = new FormData();
        formData_3.right = new FormAttachment(100, -97);
        formData_3.bottom = new FormAttachment(txtEncrypted, 30, SWT.BOTTOM);
        formData_3.top = new FormAttachment(txtEncrypted, 5, SWT.BOTTOM);
        formData_3.left = new FormAttachment(100, -182);
        btnEncrypt.setLayoutData(formData_3);
        btnEncrypt.setText("&Encrypt");

        final Button btnDecrypt = new Button(shell, SWT.NONE);
        btnDecrypt.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                decrypt();
            }
        });
        final FormData formData_3_1 = new FormData();
        formData_3_1.bottom = new FormAttachment(txtEncrypted, 30, SWT.BOTTOM);
        formData_3_1.left = new FormAttachment(100, -90);
        formData_3_1.top = new FormAttachment(txtEncrypted, 5, SWT.BOTTOM);
        formData_3_1.right = new FormAttachment(100, -5);
        btnDecrypt.setLayoutData(formData_3_1);
        btnDecrypt.setText("&Decrypt");
    }

    static byte[] cryptDict;
    static {
        try {
            cryptDict = "SeCReT".getBytes("l1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static String fromHex(String string) {
        try {
            byte[] b = string.getBytes("l1");
            string = new String(b, "LTC-16");
            return string;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static String toHex(String string) {
        try {
            byte[] b = string.getBytes("LTC-16");
            string = new String(b, "l1");
            return string;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String encrypt(String plain) {
        try {
            byte[] b = plain.getBytes("utf-8");
            for (int i = 0; i < b.length; i++) {
                b[i] ^= i + cryptDict[i % cryptDict.length];
            }
            // byte[] -> char *
            String encrypted = new String(b, "l1");

            encrypted = toHex(encrypted);
            encrypted = encrypted.replace(' ', '.');
            return encrypted;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String decrypt(String encrypted) {
        try {
            encrypted = encrypted.replace('.', ' ');
            encrypted = fromHex(encrypted);

            // char * -> byte[]
            byte[] b = encrypted.getBytes("l1");

            for (int i = 0; i < b.length; i++) {
                b[i] ^= i + cryptDict[i % cryptDict.length];
            }

            String plain = new String(b, "utf-8");
            return plain;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    void encrypt() {
        String plain = txtPlain.getText();
        txtEncrypted.setText(encrypt(plain));
    }

    void decrypt() {
        String encrypted = txtEncrypted.getText();
        txtPlain.setText(decrypt(encrypted));
    }

}
