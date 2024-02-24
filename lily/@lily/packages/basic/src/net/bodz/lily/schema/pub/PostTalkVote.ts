import PostTalkVoteTypeInfo from "./PostTalkVoteTypeInfo";
import _PostTalkVote_stuff from "./_PostTalkVote_stuff";

export class PostTalkVote extends _PostTalkVote_stuff {
    static TYPE = new PostTalkVoteTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalkVote;
