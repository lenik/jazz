import _PostTag_stuff from "./_PostTag_stuff";
import { _PostTag_stuff_Type } from "./_PostTag_stuff_Type";

export class PostTag extends _PostTag_stuff {
    static TYPE = new _PostTag_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTag;
