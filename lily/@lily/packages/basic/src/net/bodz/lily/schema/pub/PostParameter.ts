import PostParameterTypeInfo from "./PostParameterTypeInfo";
import _PostParameter_stuff from "./_PostParameter_stuff";

export class PostParameter extends _PostParameter_stuff {
    static _typeInfo: PostParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PostParameterTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameter;
