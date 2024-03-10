import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _PostTagType_stuff_Validators from "./_PostTagType_stuff_Validators";

export class _PostTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "posttag";

    readonly validators = new _PostTagType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTagType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PostTagType_stuff_TypeInfo;
