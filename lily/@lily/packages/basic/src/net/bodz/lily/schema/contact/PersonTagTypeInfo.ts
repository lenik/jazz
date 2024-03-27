import PersonTag from "./PersonTag";
import PersonTagValidators from "./PersonTagValidators";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class PersonTagTypeInfo extends _PersonTag_stuff_TypeInfo {

    readonly validators = new PersonTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PersonTag"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PersonTag();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PersonTagTypeInfo();

}

export default PersonTagTypeInfo;
