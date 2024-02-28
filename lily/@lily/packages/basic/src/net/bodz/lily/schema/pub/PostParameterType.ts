import PostParameterTypeTypeInfo from "./PostParameterTypeTypeInfo";
import _PostParameterType_stuff from "./_PostParameterType_stuff";

export class PostParameterType extends _PostParameterType_stuff<PostParameterType> {
    static _typeInfo: PostParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PostParameterTypeTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameterType;
