package net.bodz.mda.xjdoc.model;

public class DocWalker
        implements
            IDocVisitor {

    IDocVisitor before;
    IDocVisitor after;

    public DocWalker(IDocVisitor before) {
        this(before, null);
    }

    public DocWalker(IDocVisitor before, IDocVisitor after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public void packageDoc(JavaPackageDoc packageDoc) {
        if (before != null)
            before.packageDoc(packageDoc);
        if (after != null)
            after.packageDoc(packageDoc);
    }

    @Override
    public void classDoc(ClassDoc classDoc) {
        if (before != null)
            before.classDoc(classDoc);
        for (FieldDoc fieldDoc : classDoc.getFieldDocs().values())
            fieldDoc.accept(this);
        for (MethodDoc methodDoc : classDoc.getMethodDocs().values())
            methodDoc.accept(this);
        if (after != null)
            after.classDoc(classDoc);
    }

    @Override
    public void fieldDoc(FieldDoc fieldDoc) {
        if (before != null)
            before.fieldDoc(fieldDoc);
        if (after != null)
            after.fieldDoc(fieldDoc);
    }

    @Override
    public void methodDoc(MethodDoc methodDoc) {
        if (before != null)
            before.methodDoc(methodDoc);
        if (after != null)
            after.methodDoc(methodDoc);
    }

    @Deprecated
    @Override
    public void propertyDoc(PropertyDoc propertyDoc) {
        if (before != null)
            before.propertyDoc(propertyDoc);
        if (after != null)
            after.propertyDoc(propertyDoc);
    }

    @Override
    public void mutable(IMutableElementDoc doc) {
        if (before != null)
            before.mutable(doc);
        if (after != null)
            after.mutable(doc);
    }

    @Override
    public void other(IElementDoc doc) {
        if (before != null)
            before.other(doc);
        if (after != null)
            after.other(doc);
    }

}
