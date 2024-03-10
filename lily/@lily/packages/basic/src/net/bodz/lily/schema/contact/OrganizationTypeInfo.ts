import OrganizationValidators from "./OrganizationValidators";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class OrganizationTypeInfo extends _Organization_stuff_TypeInfo {

    readonly validators = new OrganizationValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.Organization"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new OrganizationTypeInfo();

}

export default OrganizationTypeInfo;
