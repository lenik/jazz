package net.bodz.mda.xjdoc.model;

public interface IDocVisitor {

    void packageDoc(JavaPackageDoc packageDoc);

    void classDoc(ClassDoc classDoc);

    void fieldDoc(FieldDoc fieldDoc);

    void methodDoc(MethodDoc methodDoc);

    @Deprecated
    void propertyDoc(PropertyDoc propertyDoc);

    void mutable(IMutableElementDoc doc);

    void other(IElementDoc doc);

}
