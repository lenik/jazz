import _PostTag_stuff from "./_PostTag_stuff";
import { _PostTag_stuffTypeInfo } from "./_PostTag_stuffTypeInfo";

export class PostTag extends _PostTag_stuff {
    static TYPE = new _PostTag_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTag;
