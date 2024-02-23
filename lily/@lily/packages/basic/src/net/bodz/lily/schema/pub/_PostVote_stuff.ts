import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecord from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";

import Post from "./Post";
import _PostVote_stuff_Type from "./_PostVote_stuff_Type";

export class _PostVote_stuff extends VoteRecord {
    static TYPE = new _PostVote_stuff_Type();

    voteScore: integer;

    parent: Post;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostVote_stuff;
