package net.bodz.violet.tran;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

@Table(name = "trancat")
@IdType(Integer.class)
public class TransportCategory
        extends CoCategory<TransportCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public TransportCategory() {
    }

    public TransportCategory(TransportCategory parent) {
        super(parent);
    }

}
