import Article from "./Article";
import ArticleValidators from "./ArticleValidators";
import _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

export class ArticleTypeInfo extends _Article_stuff_TypeInfo {

    readonly validators = new ArticleValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.Article"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Article();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleTypeInfo();

}

export default ArticleTypeInfo;
