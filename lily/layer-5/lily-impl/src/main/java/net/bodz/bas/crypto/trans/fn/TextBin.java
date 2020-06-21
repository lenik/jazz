package net.bodz.bas.crypto.trans.fn;

public class TextBin
        implements ICodeBin {

    String text;

    public TextBin(String text) {
        this.text = text;
    }

    @Override
    public String getStringForm() {
        return text;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TextBin o = (TextBin) obj;
        if (text == null) {
            if (o.text != null)
                return false;
        } else if (!text.equals(o.text))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getStringForm();
    }

}
