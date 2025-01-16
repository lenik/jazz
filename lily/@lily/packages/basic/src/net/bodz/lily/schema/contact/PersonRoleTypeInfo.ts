import PersonRole from "./PersonRole";
import PersonRoleValidators from "./PersonRoleValidators";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class PersonRoleTypeInfo extends _PersonRole_stuff_TypeInfo {

    readonly validators = new PersonRoleValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PersonRole"; }
    get icon() { return "fa-tag"; }
    get description() { return "职位关联"; }

    override create() {
        return new PersonRole();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PersonRoleTypeInfo();

}

export default PersonRoleTypeInfo;

export const PersonRole_TYPE = PersonRoleTypeInfo.INSTANCE;
