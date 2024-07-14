import CoParameterTypeInfo from "../../concrete/CoParameterTypeInfo";
import _ArticleParameterType_stuff_Validators from "./_ArticleParameterType_stuff_Validators";

export class _ArticleParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articleparm";

    readonly validators = new _ArticleParameterType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _ArticleParameterType_stuff_TypeInfo;

export const _ArticleParameterType_stuff_TYPE = _ArticleParameterType_stuff_TypeInfo.INSTANCE;
