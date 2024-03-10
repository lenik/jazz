import ArticleParameterTypeValidators from "./ArticleParameterTypeValidators";
import _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

export class ArticleParameterTypeTypeInfo extends _ArticleParameterType_stuff_TypeInfo {

    readonly validators = new ArticleParameterTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleParameterTypeTypeInfo();

}

export default ArticleParameterTypeTypeInfo;
