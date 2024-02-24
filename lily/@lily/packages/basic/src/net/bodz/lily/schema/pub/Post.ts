import PostTypeInfo from "./PostTypeInfo";
import _Post_stuff from "./_Post_stuff";

export class Post extends _Post_stuff {
    static TYPE = new PostTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Post;
