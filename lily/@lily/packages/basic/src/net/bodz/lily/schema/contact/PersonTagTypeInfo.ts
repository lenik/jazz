import PersonTagValidators from "./PersonTagValidators";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class PersonTagTypeInfo extends _PersonTag_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.PersonTag"; }
    get icon() { return "fa-tag"; }

    validators = new PersonTagValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PersonTagTypeInfo;
