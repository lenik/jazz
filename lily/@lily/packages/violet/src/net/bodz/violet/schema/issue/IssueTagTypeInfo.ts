import IssueTagValidators from "./IssueTagValidators";
import _IssueTag_stuff_TypeInfo from "./_IssueTag_stuff_TypeInfo";

export class IssueTagTypeInfo extends _IssueTag_stuff_TypeInfo {

    readonly validators = new IssueTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssueTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssueTagTypeInfo();

}

export default IssueTagTypeInfo;
