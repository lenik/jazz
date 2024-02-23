import _PostTagType_stuff from "./_PostTagType_stuff";
import { _PostTagType_stuff_Type } from "./_PostTagType_stuff_Type";

export class PostTagType extends _PostTagType_stuff<PostTagType> {
    static TYPE = new _PostTagType_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTagType;
