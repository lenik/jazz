import CoParameterTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoParameterTypeInfo";

import _IssueParameter_stuff_Validators from "./_IssueParameter_stuff_Validators";

export class _IssueParameter_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "issueparm";

    readonly validators = new _IssueParameter_stuff_Validators(this);

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

}

export default _IssueParameter_stuff_TypeInfo;

export const _IssueParameter_stuff_TYPE = _IssueParameter_stuff_TypeInfo.INSTANCE;
