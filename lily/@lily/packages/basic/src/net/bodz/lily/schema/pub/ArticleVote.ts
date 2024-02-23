import _ArticleVote_stuff from "./_ArticleVote_stuff";
import { _ArticleVote_stuffTypeInfo } from "./_ArticleVote_stuffTypeInfo";

export class ArticleVote extends _ArticleVote_stuff {
    static TYPE = new _ArticleVote_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleVote;
