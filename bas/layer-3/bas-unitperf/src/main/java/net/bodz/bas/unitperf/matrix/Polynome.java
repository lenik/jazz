package net.bodz.bas.unitperf.matrix;

public class Polynome
        extends RowVector {

    private static final long serialVersionUID = 1L;

    public Polynome(int... data) {
        super(data);
    }

    public int solve() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(data.length * 12);
        int arg = 0;
        int index = data.length - 1;
        boolean cat = false;
        while (arg < data.length) {
            if (cat)
                buf.append(" + ");
            int coeff = data[arg++];
            if (coeff != 0) {
                buf.append(coeff);
                switch (index) {
                case 0:
                    break;
                case 1:
                    buf.append("x");
                    break;
                default:
                    buf.append("x^" + index);
                    break;
                }
                cat = true;
            }
            index--;
        }
        return buf.toString();
    }

}
