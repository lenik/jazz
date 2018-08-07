package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.CoParameter;

@Table(name = "articleparm")
public class ArticleParameter
        extends CoParameter<ArticleParameter> {

    private static final long serialVersionUID = 1L;

    public ArticleParameter() {
    }

}
