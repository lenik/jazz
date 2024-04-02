import ArticleTalk from "./ArticleTalk";
import ArticleTalkValidators from "./ArticleTalkValidators";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class ArticleTalkTypeInfo extends _ArticleTalk_stuff_TypeInfo {

    readonly validators = new ArticleTalkValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleTalk"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArticleTalk();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArticleTalkTypeInfo();

}

export default ArticleTalkTypeInfo;

export const ArticleTalk_TYPE = ArticleTalkTypeInfo.INSTANCE;
