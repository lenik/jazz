import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecord from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";

import Article from "./Article";
import _ArticleVote_stuff_Type from "./_ArticleVote_stuff_Type";

export class _ArticleVote_stuff extends VoteRecord {
    static TYPE = new _ArticleVote_stuff_Type();

    voteScore: integer;

    parent: Article;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleVote_stuff;
