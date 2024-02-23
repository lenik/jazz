import _ArticleTalkVote_stuff from "./_ArticleTalkVote_stuff";
import { _ArticleTalkVote_stuffTypeInfo } from "./_ArticleTalkVote_stuffTypeInfo";

export class ArticleTalkVote extends _ArticleTalkVote_stuff {
    static TYPE = new _ArticleTalkVote_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTalkVote;
