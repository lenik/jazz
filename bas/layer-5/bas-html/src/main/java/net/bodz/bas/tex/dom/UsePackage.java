package net.bodz.bas.tex.dom;

public class UsePackage
        extends Command {

    public UsePackage(String packageName) {
        super("usepackage");
        setPackageName(packageName);
    }

    public String getPackageName() {
        return getData();
    }

    public void setPackageName(String packageName) {
        setData(packageName);
    }

    @Override
    public void toString(StringBuilder sb) {
        super.toString(sb);
        sb.append('\n');
    }

}
