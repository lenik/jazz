import ArticleTagType from "./ArticleTagType";
import ArticleTagTypeValidators from "./ArticleTagTypeValidators";
import _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

export class ArticleTagTypeTypeInfo extends _ArticleTagType_stuff_TypeInfo {

    readonly validators = new ArticleTagTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTagType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleTagType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleTagTypeTypeInfo();

}

export default ArticleTagTypeTypeInfo;

export const ArticleTagType_TYPE = ArticleTagTypeTypeInfo.INSTANCE;
