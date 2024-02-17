
import { * as validators } from "./PersonValidators";
import type { _PostTalk_stuff } from "./_PostTalk_stuff";

export class PostTalk extends _PostTalk_stuff<PostTalk> {
    static TYPE = new PostTalkType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
