import ArticleVoteTypeInfo from "./ArticleVoteTypeInfo";
import _ArticleVote_stuff from "./_ArticleVote_stuff";

export class ArticleVote extends _ArticleVote_stuff {

    static _typeInfo: ArticleVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleVote;
