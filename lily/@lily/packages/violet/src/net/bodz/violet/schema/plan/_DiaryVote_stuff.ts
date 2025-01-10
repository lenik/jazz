import type { int, long } from "skel01-core/src/lang/basetype";
import VoteRecord from "@lily/basic/src/net/bodz/lily/concrete/VoteRecord";

import type Diary from "./Diary";
import _DiaryVote_stuff_TypeInfo from "./_DiaryVote_stuff_TypeInfo";

export class _DiaryVote_stuff extends VoteRecord {

    static _typeInfo: _DiaryVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _DiaryVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: Diary;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _DiaryVote_stuff;
