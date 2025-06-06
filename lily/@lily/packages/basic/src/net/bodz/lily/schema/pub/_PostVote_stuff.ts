import type { int, long } from "skel01-core/src/lang/basetype";

import VoteRecord from "../../concrete/VoteRecord";
import type Post from "./Post";
import _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

export class _PostVote_stuff extends VoteRecord {

    static _typeInfo: _PostVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PostVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: Post;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostVote_stuff;
