import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _PostTagType_stuff_Validators from "./_PostTagType_stuff_Validators";

export class _PostTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "posttag";

    get name() { return "net.bodz.lily.schema.pub.PostTagType"; }
    get icon() { return "fa-tag"; }

    validators = new _PostTagType_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor(selfType: any) {
        super(selfType);
    }

}

export default _PostTagType_stuff_TypeInfo;
