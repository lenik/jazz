import ArticleVoteTypeInfo from "./ArticleVoteTypeInfo";
import _ArticleVote_stuff from "./_ArticleVote_stuff";

export class ArticleVote extends _ArticleVote_stuff {
    static TYPE = new ArticleVoteTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleVote;
