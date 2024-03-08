import ArticleCategoryValidators from "./ArticleCategoryValidators";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class ArticleCategoryTypeInfo extends _ArticleCategory_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleCategory"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleCategoryValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleCategoryTypeInfo;
