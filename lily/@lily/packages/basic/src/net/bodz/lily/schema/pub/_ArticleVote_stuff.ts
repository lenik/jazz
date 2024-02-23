import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecord from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";

import Article from "./Article";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class _ArticleVote_stuff extends VoteRecord {
    static TYPE = new _ArticleVote_stuff_TypeInfo();

    voteScore: integer;

    parent: Article;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleVote_stuff;
