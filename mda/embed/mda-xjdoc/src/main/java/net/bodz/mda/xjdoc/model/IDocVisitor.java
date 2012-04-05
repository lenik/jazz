package net.bodz.mda.xjdoc.model;

public interface IDocVisitor {

    void visit(ElementDoc elementDoc);

    void visit(ClassDoc classDoc);

    void visit(FieldDoc fieldDoc);

    void visit(MethodDoc methodDoc);

}
