import ArticleBackref from "./ArticleBackref";
import ArticleBackrefValidators from "./ArticleBackrefValidators";
import _ArticleBackref_stuff_TypeInfo from "./_ArticleBackref_stuff_TypeInfo";

export class ArticleBackrefTypeInfo extends _ArticleBackref_stuff_TypeInfo {

    readonly validators = new ArticleBackrefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleBackref"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleBackref();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleBackrefTypeInfo();

}

export default ArticleBackrefTypeInfo;
