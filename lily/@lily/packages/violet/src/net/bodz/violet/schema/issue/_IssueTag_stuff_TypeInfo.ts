import CoTagTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _IssueTag_stuff_Validators from "./_IssueTag_stuff_Validators";

export class _IssueTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "issuetag";

    readonly validators = new _IssueTag_stuff_Validators(this);

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

}

export default _IssueTag_stuff_TypeInfo;

export const _IssueTag_stuff_TYPE = _IssueTag_stuff_TypeInfo.INSTANCE;
