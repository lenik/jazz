import ArticleTalkVoteTypeInfo from "./ArticleTalkVoteTypeInfo";
import _ArticleTalkVote_stuff from "./_ArticleTalkVote_stuff";

export class ArticleTalkVote extends _ArticleTalkVote_stuff {
    static TYPE = new ArticleTalkVoteTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTalkVote;
