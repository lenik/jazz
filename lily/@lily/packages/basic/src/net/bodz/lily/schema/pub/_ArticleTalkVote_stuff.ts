import type { int, long } from "@skeljs/core/src/lang/basetype";

import VoteRecord from "../../concrete/VoteRecord";
import type ArticleTalk from "./ArticleTalk";
import _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

export class _ArticleTalkVote_stuff extends VoteRecord {

    static _typeInfo: _ArticleTalkVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArticleTalkVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: ArticleTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalkVote_stuff;
