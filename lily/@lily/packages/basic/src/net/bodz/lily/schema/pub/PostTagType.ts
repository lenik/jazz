import PostTagTypeTypeInfo from "./PostTagTypeTypeInfo";
import _PostTagType_stuff from "./_PostTagType_stuff";

export class PostTagType extends _PostTagType_stuff<PostTagType> {
    static TYPE = new PostTagTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTagType;
