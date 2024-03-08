import PersonRoleValidators from "./PersonRoleValidators";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class PersonRoleTypeInfo extends _PersonRole_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.PersonRole"; }
    get icon() { return "fa-tag"; }

    validators = new PersonRoleValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PersonRoleTypeInfo;
