import CoPhaseTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoPhaseTypeInfo";

import _IssuePhase_stuff_Validators from "./_IssuePhase_stuff_Validators";

export class _IssuePhase_stuff_TypeInfo extends CoPhaseTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "issuephase";

    readonly validators = new _IssuePhase_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssuePhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new _IssuePhase_stuff_TypeInfo();

}

export default _IssuePhase_stuff_TypeInfo;

export const _IssuePhase_stuff_TYPE = _IssuePhase_stuff_TypeInfo.INSTANCE;
