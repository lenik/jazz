
import { * as validators } from "./PersonValidators";
import type { _ArticleVote_stuff } from "./_ArticleVote_stuff";

export class ArticleVote extends _ArticleVote_stuff {
    static TYPE = new ArticleVoteType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
