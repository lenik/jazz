import type { int, long } from "skel01-core/src/lang/basetype";
import VoteRecord from "lily-basic/src/net/bodz/lily/concrete/VoteRecord";

import type Plan from "./Plan";
import _PlanVote_stuff_TypeInfo from "./_PlanVote_stuff_TypeInfo";

export class _PlanVote_stuff extends VoteRecord {

    static _typeInfo: _PlanVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PlanVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: Plan;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PlanVote_stuff;
