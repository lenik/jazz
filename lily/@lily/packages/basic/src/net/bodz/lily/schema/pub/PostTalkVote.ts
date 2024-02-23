import _PostTalkVote_stuff from "./_PostTalkVote_stuff";
import { _PostTalkVote_stuff_Type } from "./_PostTalkVote_stuff_Type";

export class PostTalkVote extends _PostTalkVote_stuff {
    static TYPE = new _PostTalkVote_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalkVote;
