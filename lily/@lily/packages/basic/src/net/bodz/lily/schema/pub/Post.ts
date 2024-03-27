import PostTypeInfo from "./PostTypeInfo";
import _Post_stuff from "./_Post_stuff";

export class Post extends _Post_stuff {

    static _typeInfo: PostTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PostTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default Post;
