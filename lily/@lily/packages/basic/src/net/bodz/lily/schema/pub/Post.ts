import _Post_stuff from "./_Post_stuff";
import { _Post_stuff_Type } from "./_Post_stuff_Type";

export class Post extends _Post_stuff {
    static TYPE = new _Post_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Post;
