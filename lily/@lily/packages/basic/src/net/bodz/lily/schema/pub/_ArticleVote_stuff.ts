import type { int, long } from "skel01-core/src/lang/basetype";

import VoteRecord from "../../concrete/VoteRecord";
import type Article from "./Article";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class _ArticleVote_stuff extends VoteRecord {

    static _typeInfo: _ArticleVote_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArticleVote_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    voteScore: int;

    parent: Article;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleVote_stuff;
