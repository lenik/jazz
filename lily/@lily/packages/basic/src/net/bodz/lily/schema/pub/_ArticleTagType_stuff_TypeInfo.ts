import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _ArticleTagType_stuff_Validators from "./_ArticleTagType_stuff_Validators";

export class _ArticleTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articletag";

    get name() { return "net.bodz.lily.schema.pub.ArticleTagType"; }
    get icon() { return "fa-tag"; }

    validators = new _ArticleTagType_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default _ArticleTagType_stuff_TypeInfo;
