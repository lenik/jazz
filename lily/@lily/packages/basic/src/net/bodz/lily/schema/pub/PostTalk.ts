import PostTalkTypeInfo from "./PostTalkTypeInfo";
import _PostTalk_stuff from "./_PostTalk_stuff";

export class PostTalk extends _PostTalk_stuff<PostTalk> {
    static TYPE = new PostTalkTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalk;
