
import { * as validators } from "./PersonValidators";
import type { _Post_stuff } from "./_Post_stuff";

export class Post extends _Post_stuff {
    static TYPE = new PostType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
