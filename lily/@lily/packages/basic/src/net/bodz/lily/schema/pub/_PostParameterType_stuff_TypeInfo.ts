import CoParameterTypeInfo from "../../concrete/CoParameterTypeInfo";
import _PostParameterType_stuff_Validators from "./_PostParameterType_stuff_Validators";

export class _PostParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postparm";

    readonly validators = new _PostParameterType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PostParameterType_stuff_TypeInfo;

export const _PostParameterType_stuff_TYPE = _PostParameterType_stuff_TypeInfo.INSTANCE;
