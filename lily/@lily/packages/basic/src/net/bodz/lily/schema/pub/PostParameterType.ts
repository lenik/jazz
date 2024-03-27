import PostParameterTypeTypeInfo from "./PostParameterTypeTypeInfo";
import _PostParameterType_stuff from "./_PostParameterType_stuff";

export class PostParameterType extends _PostParameterType_stuff<PostParameterType> {

    static _typeInfo: PostParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PostParameterTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PostParameterType;
