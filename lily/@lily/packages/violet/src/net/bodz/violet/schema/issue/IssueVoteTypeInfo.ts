import IssueVote from "./IssueVote";
import IssueVoteValidators from "./IssueVoteValidators";
import _IssueVote_stuff_TypeInfo from "./_IssueVote_stuff_TypeInfo";

export class IssueVoteTypeInfo extends _IssueVote_stuff_TypeInfo {

    readonly validators = new IssueVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssueVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new IssueVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssueVoteTypeInfo();

}

export default IssueVoteTypeInfo;
