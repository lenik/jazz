import PostBackrefTypeInfo from "./PostBackrefTypeInfo";
import _PostBackref_stuff from "./_PostBackref_stuff";

export class PostBackref extends _PostBackref_stuff {

    static _typeInfo: PostBackrefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PostBackrefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostBackref;
