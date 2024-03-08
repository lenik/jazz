import ArticleBackrefValidators from "./ArticleBackrefValidators";
import _ArticleBackref_stuff_TypeInfo from "./_ArticleBackref_stuff_TypeInfo";

export class ArticleBackrefTypeInfo extends _ArticleBackref_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleBackref"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleBackrefValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleBackrefTypeInfo;
