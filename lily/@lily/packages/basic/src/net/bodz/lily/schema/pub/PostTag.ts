
import { * as validators } from "./PersonValidators";
import type { _PostTag_stuff } from "./_PostTag_stuff";

export class PostTag extends _PostTag_stuff {
    static TYPE = new PostTagType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
