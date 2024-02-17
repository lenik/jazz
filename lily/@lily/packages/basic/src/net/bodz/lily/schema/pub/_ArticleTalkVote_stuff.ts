
import type { VoteRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { ArticleTalk } from "./ArticleTalk";
import type { _ArticleTalkVote_stuff_Type } from "./_ArticleTalkVote_stuff_Type";

export class _ArticleTalkVote_stuff extends VoteRecord {
    static TYPE = new _ArticleTalkVote_stuff_Type();

    voteScore: int;

    parent: ArticleTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}
