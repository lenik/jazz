import type { integer, long } from "@skeljs/core/src/lang/type";

import VoteRecord from "../../concrete/VoteRecord";
import type ArticleTalk from "./ArticleTalk";
import _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

export class _ArticleTalkVote_stuff extends VoteRecord {
    static TYPE = new _ArticleTalkVote_stuff_TypeInfo();

    voteScore: integer;

    parent: ArticleTalk;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalkVote_stuff;
