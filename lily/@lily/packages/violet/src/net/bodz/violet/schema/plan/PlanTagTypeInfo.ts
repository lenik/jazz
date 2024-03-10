import PlanTagValidators from "./PlanTagValidators";
import _PlanTag_stuff_TypeInfo from "./_PlanTag_stuff_TypeInfo";

export class PlanTagTypeInfo extends _PlanTag_stuff_TypeInfo {

    readonly validators = new PlanTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanTagTypeInfo();

}

export default PlanTagTypeInfo;
