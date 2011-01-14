package net.bodz.bas.type.validator;

import java.io.File;

import net.bodz.bas.traits.AbstractValidator;
import net.bodz.bas.traits.ValidateException;

public class FileAccessValidator
        extends AbstractValidator<File> {

    public static final int READ = 1;
    public static final int WRITE = 2;
    public static final int EXECUTE = 4;
    public static final int DIRECTORY = 8;
    public static final int FILE = 16;
    public static final int TEXT = 32;

    private int mode;

    public FileAccessValidator(int mode) {
        this.mode = mode;
    }

    public FileAccessValidator(String mode) {
        int m = 0;
        for (char c : mode.toLowerCase().toCharArray())
            switch (c) {
            case 'r':
                m |= READ;
                break;
            case 'w':
                m |= WRITE;
                break;
            case 'x':
                m |= EXECUTE;
                break;
            case 'd':
                m |= DIRECTORY;
                break;
            case 'f':
                m |= FILE;
                break;
            case 'T':
                m |= TEXT;
                break;
            case '+':
                m |= READ | WRITE;
                break;
            default:
                throw new IllegalArgumentException("invalid mode char: " + c);
            }
        this.mode = m;
    }

    private boolean set(int mask) {
        return (mode & mask) != 0;
    }

    @Override
    public void validate(File file)
            throws ValidateException {
        File f = file;
        if (set(READ) && !f.canRead())
            throw new ValidateException("can\'t read " + f);
        if (set(WRITE) && !f.canWrite())
            throw new ValidateException("can\'t write " + f);
        if (set(EXECUTE) && !f.canExecute())
            throw new ValidateException("can\'t execute " + f);
        if (set(DIRECTORY) && !f.isDirectory())
            throw new ValidateException("not a directory " + f);
        if (set(FILE) && !f.isFile())
            throw new ValidateException("not a file " + f);
        if (set(TEXT) && !Files.isText(f))
            throw new ValidateException("not a text file " + f);
    }

}
