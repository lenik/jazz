import PolicyValidators from "./PolicyValidators";
import _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

export class PolicyTypeInfo extends _Policy_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.Policy"; }
    get icon() { return "fa-tag"; }
    get description() { return "Security Policy"; }

    validators = new PolicyValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PolicyTypeInfo;
