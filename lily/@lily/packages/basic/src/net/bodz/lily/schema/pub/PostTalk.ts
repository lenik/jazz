import _PostTalk_stuff from "./_PostTalk_stuff";
import { _PostTalk_stuff_Type } from "./_PostTalk_stuff_Type";

export class PostTalk extends _PostTalk_stuff<PostTalk> {
    static TYPE = new _PostTalk_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTalk;
