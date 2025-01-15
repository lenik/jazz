import type { int, long } from "skel01-core/src/lang/basetype";
import VoteRecord from "lily-basic/src/net/bodz/lily/concrete/VoteRecord";

import type PlanDo from "./PlanDo";
import _PlanDoVote_stuff_TypeInfo from "./_PlanDoVote_stuff_TypeInfo";

export class _PlanDoVote_stuff extends VoteRecord {

    static _typeInfo: _PlanDoVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PlanDoVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: PlanDo;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PlanDoVote_stuff;
