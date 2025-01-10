import type { int, long } from "skel01-core/src/lang/basetype";
import VoteRecord from "@lily/basic/src/net/bodz/lily/concrete/VoteRecord";

import type TestQuestion from "./TestQuestion";
import _TestQuestionVote_stuff_TypeInfo from "./_TestQuestionVote_stuff_TypeInfo";

export class _TestQuestionVote_stuff extends VoteRecord {

    static _typeInfo: _TestQuestionVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestQuestionVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: TestQuestion;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TestQuestionVote_stuff;
