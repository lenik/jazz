package net.bodz.xml.models.pdb;

public interface PDBElement {

    void accept(PDBVisitor visitor);

}
