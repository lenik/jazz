import IssueCategoryValidators from "./IssueCategoryValidators";
import _IssueCategory_stuff_TypeInfo from "./_IssueCategory_stuff_TypeInfo";

export class IssueCategoryTypeInfo extends _IssueCategory_stuff_TypeInfo {

    readonly validators = new IssueCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssueCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssueCategoryTypeInfo();

}

export default IssueCategoryTypeInfo;