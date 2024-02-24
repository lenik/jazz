import PostParameterTypeTypeInfo from "./PostParameterTypeTypeInfo";
import _PostParameterType_stuff from "./_PostParameterType_stuff";

export class PostParameterType extends _PostParameterType_stuff<PostParameterType> {
    static TYPE = new PostParameterTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameterType;
