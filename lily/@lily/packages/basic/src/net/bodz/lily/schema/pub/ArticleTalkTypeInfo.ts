import ArticleTalkValidators from "./ArticleTalkValidators";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class ArticleTalkTypeInfo extends _ArticleTalk_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleTalk"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleTalkValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ArticleTalkTypeInfo;
