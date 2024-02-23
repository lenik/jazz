import _PostVote_stuff from "./_PostVote_stuff";
import { _PostVote_stuffTypeInfo } from "./_PostVote_stuffTypeInfo";

export class PostVote extends _PostVote_stuff {
    static TYPE = new _PostVote_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostVote;
