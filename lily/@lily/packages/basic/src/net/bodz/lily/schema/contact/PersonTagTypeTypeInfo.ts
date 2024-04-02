import PersonTagType from "./PersonTagType";
import PersonTagTypeValidators from "./PersonTagTypeValidators";
import _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

export class PersonTagTypeTypeInfo extends _PersonTagType_stuff_TypeInfo {

    readonly validators = new PersonTagTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PersonTagType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PersonTagType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PersonTagTypeTypeInfo();

}

export default PersonTagTypeTypeInfo;

export const PersonTagType_TYPE = PersonTagTypeTypeInfo.INSTANCE;
