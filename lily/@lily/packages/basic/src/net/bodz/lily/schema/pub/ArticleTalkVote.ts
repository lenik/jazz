import ArticleTalkVoteTypeInfo from "./ArticleTalkVoteTypeInfo";
import _ArticleTalkVote_stuff from "./_ArticleTalkVote_stuff";

export class ArticleTalkVote extends _ArticleTalkVote_stuff {

    static _typeInfo: ArticleTalkVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleTalkVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleTalkVote;
