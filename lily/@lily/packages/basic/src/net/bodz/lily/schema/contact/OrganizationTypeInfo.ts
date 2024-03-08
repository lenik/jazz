import OrganizationValidators from "./OrganizationValidators";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class OrganizationTypeInfo extends _Organization_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Organization"; }
    get icon() { return "fa-tag"; }

    validators = new OrganizationValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default OrganizationTypeInfo;
