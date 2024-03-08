import ArticleParameterValidators from "./ArticleParameterValidators";
import _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class ArticleParameterTypeInfo extends _ArticleParameter_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleParameter"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleParameterValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleParameterTypeInfo;
