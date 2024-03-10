import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _PersonTagType_stuff_Validators from "./_PersonTagType_stuff_Validators";

export class _PersonTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "persontag";

    readonly validators = new _PersonTagType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PersonTagType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PersonTagType_stuff_TypeInfo;
