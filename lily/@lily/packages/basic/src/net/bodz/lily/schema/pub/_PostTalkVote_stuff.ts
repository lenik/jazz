import type { int, long } from "@skeljs/core/src/lang/basetype";

import VoteRecord from "../../concrete/VoteRecord";
import type PostTalk from "./PostTalk";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class _PostTalkVote_stuff extends VoteRecord {

    static _typeInfo: _PostTalkVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PostTalkVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: PostTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTalkVote_stuff;
