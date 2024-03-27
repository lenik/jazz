import PlanVote from "./PlanVote";
import PlanVoteValidators from "./PlanVoteValidators";
import _PlanVote_stuff_TypeInfo from "./_PlanVote_stuff_TypeInfo";

export class PlanVoteTypeInfo extends _PlanVote_stuff_TypeInfo {

    readonly validators = new PlanVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanVoteTypeInfo();

}

export default PlanVoteTypeInfo;
