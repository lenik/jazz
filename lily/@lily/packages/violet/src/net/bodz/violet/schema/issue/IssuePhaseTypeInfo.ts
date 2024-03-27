import IssuePhase from "./IssuePhase";
import IssuePhaseValidators from "./IssuePhaseValidators";
import _IssuePhase_stuff_TypeInfo from "./_IssuePhase_stuff_TypeInfo";

export class IssuePhaseTypeInfo extends _IssuePhase_stuff_TypeInfo {

    readonly validators = new IssuePhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssuePhase"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new IssuePhase();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssuePhaseTypeInfo();

}

export default IssuePhaseTypeInfo;
