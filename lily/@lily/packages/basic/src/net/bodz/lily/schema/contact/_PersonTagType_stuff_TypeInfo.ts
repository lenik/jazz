import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _PersonTagType_stuff_Validators from "./_PersonTagType_stuff_Validators";

export class _PersonTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "persontag";

    get name() { return "net.bodz.lily.schema.contact.PersonTagType"; }
    get icon() { return "fa-tag"; }

    validators = new _PersonTagType_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default _PersonTagType_stuff_TypeInfo;
