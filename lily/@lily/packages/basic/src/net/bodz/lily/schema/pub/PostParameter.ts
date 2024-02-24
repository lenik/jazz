import PostParameterTypeInfo from "./PostParameterTypeInfo";
import _PostParameter_stuff from "./_PostParameter_stuff";

export class PostParameter extends _PostParameter_stuff {
    static TYPE = new PostParameterTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameter;
