import PostVoteTypeInfo from "./PostVoteTypeInfo";
import _PostVote_stuff from "./_PostVote_stuff";

export class PostVote extends _PostVote_stuff {
    static _typeInfo: PostVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PostVoteTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostVote;
