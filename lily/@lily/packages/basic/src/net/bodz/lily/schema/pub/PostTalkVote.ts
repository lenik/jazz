
import { * as validators } from "./PersonValidators";
import type { _PostTalkVote_stuff } from "./_PostTalkVote_stuff";

export class PostTalkVote extends _PostTalkVote_stuff {
    static TYPE = new PostTalkVoteType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
