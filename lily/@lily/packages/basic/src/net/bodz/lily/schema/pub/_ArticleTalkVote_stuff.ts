import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecord from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";

import ArticleTalk from "./ArticleTalk";
import _ArticleTalkVote_stuff_Type from "./_ArticleTalkVote_stuff_Type";

export class _ArticleTalkVote_stuff extends VoteRecord {
    static TYPE = new _ArticleTalkVote_stuff_Type();

    voteScore: integer;

    parent: ArticleTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalkVote_stuff;
