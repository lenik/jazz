import _PostParameterType_stuff from "./_PostParameterType_stuff";
import { _PostParameterType_stuffTypeInfo } from "./_PostParameterType_stuffTypeInfo";

export class PostParameterType extends _PostParameterType_stuff<PostParameterType> {
    static TYPE = new _PostParameterType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameterType;
