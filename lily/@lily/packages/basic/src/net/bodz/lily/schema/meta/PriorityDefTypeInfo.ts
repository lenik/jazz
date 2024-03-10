import PriorityDefValidators from "./PriorityDefValidators";
import _PriorityDef_stuff_TypeInfo from "./_PriorityDef_stuff_TypeInfo";

export class PriorityDefTypeInfo extends _PriorityDef_stuff_TypeInfo {

    readonly validators = new PriorityDefValidators(this);

    constructor() {
        super();
        this.selfType = this;
    }

    get name() { return "net.bodz.lily.schema.meta.PriorityDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Priority"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PriorityDefTypeInfo();

}

export default PriorityDefTypeInfo;
