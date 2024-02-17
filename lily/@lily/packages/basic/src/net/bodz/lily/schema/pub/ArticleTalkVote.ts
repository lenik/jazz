
import { * as validators } from "./PersonValidators";
import type { _ArticleTalkVote_stuff } from "./_ArticleTalkVote_stuff";

export class ArticleTalkVote extends _ArticleTalkVote_stuff {
    static TYPE = new ArticleTalkVoteType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
