import PlanDoVoteValidators from "./PlanDoVoteValidators";
import _PlanDoVote_stuff_TypeInfo from "./_PlanDoVote_stuff_TypeInfo";

export class PlanDoVoteTypeInfo extends _PlanDoVote_stuff_TypeInfo {

    readonly validators = new PlanDoVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanDoVoteTypeInfo();

}

export default PlanDoVoteTypeInfo;