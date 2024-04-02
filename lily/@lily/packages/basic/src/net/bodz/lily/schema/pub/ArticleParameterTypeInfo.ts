import ArticleParameter from "./ArticleParameter";
import ArticleParameterValidators from "./ArticleParameterValidators";
import _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class ArticleParameterTypeInfo extends _ArticleParameter_stuff_TypeInfo {

    readonly validators = new ArticleParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleParameterTypeInfo();

}

export default ArticleParameterTypeInfo;

export const ArticleParameter_TYPE = ArticleParameterTypeInfo.INSTANCE;
