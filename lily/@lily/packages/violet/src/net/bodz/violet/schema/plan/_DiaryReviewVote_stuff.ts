import type { int, long } from "skel01-core/src/lang/basetype";
import VoteRecord from "@lily/basic/src/net/bodz/lily/concrete/VoteRecord";

import type DiaryReview from "./DiaryReview";
import _DiaryReviewVote_stuff_TypeInfo from "./_DiaryReviewVote_stuff_TypeInfo";

export class _DiaryReviewVote_stuff extends VoteRecord {

    static _typeInfo: _DiaryReviewVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _DiaryReviewVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: DiaryReview;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _DiaryReviewVote_stuff;
