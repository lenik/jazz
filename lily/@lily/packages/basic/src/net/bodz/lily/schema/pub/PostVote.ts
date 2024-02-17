
import { * as validators } from "./PersonValidators";
import type { _PostVote_stuff } from "./_PostVote_stuff";

export class PostVote extends _PostVote_stuff {
    static TYPE = new PostVoteType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
