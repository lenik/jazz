import _Post_stuff from "./_Post_stuff";
import { _Post_stuffTypeInfo } from "./_Post_stuffTypeInfo";

export class Post extends _Post_stuff {
    static TYPE = new _Post_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Post;
