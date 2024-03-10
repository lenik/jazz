import PlanDoTagValidators from "./PlanDoTagValidators";
import _PlanDoTag_stuff_TypeInfo from "./_PlanDoTag_stuff_TypeInfo";

export class PlanDoTagTypeInfo extends _PlanDoTag_stuff_TypeInfo {

    readonly validators = new PlanDoTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanDoTagTypeInfo();

}

export default PlanDoTagTypeInfo;
