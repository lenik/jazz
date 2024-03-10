import IssueParameterValidators from "./IssueParameterValidators";
import _IssueParameter_stuff_TypeInfo from "./_IssueParameter_stuff_TypeInfo";

export class IssueParameterTypeInfo extends _IssueParameter_stuff_TypeInfo {

    readonly validators = new IssueParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssueParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssueParameterTypeInfo();

}

export default IssueParameterTypeInfo;
