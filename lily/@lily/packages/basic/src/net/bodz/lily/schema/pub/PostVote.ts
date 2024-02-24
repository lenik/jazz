import PostVoteTypeInfo from "./PostVoteTypeInfo";
import _PostVote_stuff from "./_PostVote_stuff";

export class PostVote extends _PostVote_stuff {
    static TYPE = new PostVoteTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostVote;
