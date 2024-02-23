import _PostTalkVote_stuff from "./_PostTalkVote_stuff";
import { _PostTalkVote_stuffTypeInfo } from "./_PostTalkVote_stuffTypeInfo";

export class PostTalkVote extends _PostTalkVote_stuff {
    static TYPE = new _PostTalkVote_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalkVote;
