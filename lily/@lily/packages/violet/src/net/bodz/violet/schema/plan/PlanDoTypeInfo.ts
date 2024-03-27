import PlanDo from "./PlanDo";
import PlanDoValidators from "./PlanDoValidators";
import _PlanDo_stuff_TypeInfo from "./_PlanDo_stuff_TypeInfo";

export class PlanDoTypeInfo extends _PlanDo_stuff_TypeInfo {

    readonly validators = new PlanDoValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDo"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanDo();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanDoTypeInfo();

}

export default PlanDoTypeInfo;
