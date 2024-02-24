import PostTagTypeInfo from "./PostTagTypeInfo";
import _PostTag_stuff from "./_PostTag_stuff";

export class PostTag extends _PostTag_stuff {
    static TYPE = new PostTagTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTag;
