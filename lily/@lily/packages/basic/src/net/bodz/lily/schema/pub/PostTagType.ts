import _PostTagType_stuff from "./_PostTagType_stuff";
import { _PostTagType_stuffTypeInfo } from "./_PostTagType_stuffTypeInfo";

export class PostTagType extends _PostTagType_stuff<PostTagType> {
    static TYPE = new _PostTagType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTagType;
