import Issue from "./Issue";
import IssueValidators from "./IssueValidators";
import _Issue_stuff_TypeInfo from "./_Issue_stuff_TypeInfo";

export class IssueTypeInfo extends _Issue_stuff_TypeInfo {

    readonly validators = new IssueValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.Issue"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Issue();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssueTypeInfo();

}

export default IssueTypeInfo;

export const Issue_TYPE = IssueTypeInfo.INSTANCE;
