import PolicyValidators from "./PolicyValidators";
import _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

export class PolicyTypeInfo extends _Policy_stuff_TypeInfo {

    readonly validators = new PolicyValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.Policy"; }
    get icon() { return "fa-tag"; }
    get description() { return "Security Policy"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PolicyTypeInfo();

}

export default PolicyTypeInfo;
