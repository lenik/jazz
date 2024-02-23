import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecord from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";

import PostTalk from "./PostTalk";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class _PostTalkVote_stuff extends VoteRecord {
    static TYPE = new _PostTalkVote_stuff_TypeInfo();

    voteScore: integer;

    parent: PostTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTalkVote_stuff;
