import type { int, long } from "skel01-core/src/lang/basetype";
import VoteRecord from "@lily/basic/src/net/bodz/lily/concrete/VoteRecord";

import type Issue from "./Issue";
import _IssueVote_stuff_TypeInfo from "./_IssueVote_stuff_TypeInfo";

export class _IssueVote_stuff extends VoteRecord {

    static _typeInfo: _IssueVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _IssueVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: Issue;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _IssueVote_stuff;
