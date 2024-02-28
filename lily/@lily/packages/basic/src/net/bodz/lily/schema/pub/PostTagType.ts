import PostTagTypeTypeInfo from "./PostTagTypeTypeInfo";
import _PostTagType_stuff from "./_PostTagType_stuff";

export class PostTagType extends _PostTagType_stuff<PostTagType> {
    static _typeInfo: PostTagTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PostTagTypeTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostTagType;
