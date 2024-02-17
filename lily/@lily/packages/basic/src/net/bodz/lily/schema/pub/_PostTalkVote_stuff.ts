
import type { VoteRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { PostTalk } from "./PostTalk";
import type { _PostTalkVote_stuff_Type } from "./_PostTalkVote_stuff_Type";

export class _PostTalkVote_stuff extends VoteRecord {
    static TYPE = new _PostTalkVote_stuff_Type();

    voteScore: int;

    parent: PostTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}
