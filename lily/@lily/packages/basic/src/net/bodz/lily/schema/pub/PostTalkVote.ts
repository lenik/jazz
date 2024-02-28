import PostTalkVoteTypeInfo from "./PostTalkVoteTypeInfo";
import _PostTalkVote_stuff from "./_PostTalkVote_stuff";

export class PostTalkVote extends _PostTalkVote_stuff {
    static _typeInfo: PostTalkVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PostTalkVoteTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalkVote;
