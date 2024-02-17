
import type { VoteRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Article } from "./Article";
import type { _ArticleVote_stuff_Type } from "./_ArticleVote_stuff_Type";

export class _ArticleVote_stuff extends VoteRecord {
    static TYPE = new _ArticleVote_stuff_Type();

    voteScore: int;

    parent: Article;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}
