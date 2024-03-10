import PostTalkTypeInfo from "./PostTalkTypeInfo";
import _PostTalk_stuff from "./_PostTalk_stuff";

export class PostTalk extends _PostTalk_stuff<PostTalk> {

    static _typeInfo: PostTalkTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PostTalkTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalk;
