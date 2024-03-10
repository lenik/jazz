import type { int, long } from "@skeljs/core/src/lang/basetype";
import VoteRecord from "@lily/basic/src/net/bodz/lily/concrete/VoteRecord";

import type TestQuestionTalk from "./TestQuestionTalk";
import _TestQuestionTalkVote_stuff_TypeInfo from "./_TestQuestionTalkVote_stuff_TypeInfo";

export class _TestQuestionTalkVote_stuff extends VoteRecord {

    static _typeInfo: _TestQuestionTalkVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestQuestionTalkVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: TestQuestionTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TestQuestionTalkVote_stuff;
