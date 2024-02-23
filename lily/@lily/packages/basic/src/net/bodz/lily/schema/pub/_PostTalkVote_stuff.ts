import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecord from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";

import PostTalk from "./PostTalk";
import _PostTalkVote_stuff_Type from "./_PostTalkVote_stuff_Type";

export class _PostTalkVote_stuff extends VoteRecord {
    static TYPE = new _PostTalkVote_stuff_Type();

    voteScore: integer;

    parent: PostTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTalkVote_stuff;
