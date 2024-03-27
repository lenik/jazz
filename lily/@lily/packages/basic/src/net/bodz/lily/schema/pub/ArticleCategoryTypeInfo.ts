import ArticleCategory from "./ArticleCategory";
import ArticleCategoryValidators from "./ArticleCategoryValidators";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class ArticleCategoryTypeInfo extends _ArticleCategory_stuff_TypeInfo {

    readonly validators = new ArticleCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleCategoryTypeInfo();

}

export default ArticleCategoryTypeInfo;
