import PostTagTypeInfo from "./PostTagTypeInfo";
import _PostTag_stuff from "./_PostTag_stuff";

export class PostTag extends _PostTag_stuff {

    static _typeInfo: PostTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PostTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PostTag;
