
import type { VoteRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Post } from "./Post";
import type { _PostVote_stuff_Type } from "./_PostVote_stuff_Type";

export class _PostVote_stuff extends VoteRecord {
    static TYPE = new _PostVote_stuff_Type();

    voteScore: int;

    parent: Post;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}
