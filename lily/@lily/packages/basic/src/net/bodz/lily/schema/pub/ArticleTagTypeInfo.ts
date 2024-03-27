import ArticleTag from "./ArticleTag";
import ArticleTagValidators from "./ArticleTagValidators";
import _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

export class ArticleTagTypeInfo extends _ArticleTag_stuff_TypeInfo {

    readonly validators = new ArticleTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTag"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleTag();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleTagTypeInfo();

}

export default ArticleTagTypeInfo;
