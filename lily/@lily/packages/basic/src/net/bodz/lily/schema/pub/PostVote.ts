import _PostVote_stuff from "./_PostVote_stuff";
import { _PostVote_stuff_Type } from "./_PostVote_stuff_Type";

export class PostVote extends _PostVote_stuff {
    static TYPE = new _PostVote_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostVote;
