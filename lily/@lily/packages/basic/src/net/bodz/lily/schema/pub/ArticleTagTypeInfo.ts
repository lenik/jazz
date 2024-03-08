import ArticleTagValidators from "./ArticleTagValidators";
import _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

export class ArticleTagTypeInfo extends _ArticleTag_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleTag"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleTagValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleTagTypeInfo;
