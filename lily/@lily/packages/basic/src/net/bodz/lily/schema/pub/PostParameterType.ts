import _PostParameterType_stuff from "./_PostParameterType_stuff";
import { _PostParameterType_stuff_Type } from "./_PostParameterType_stuff_Type";

export class PostParameterType extends _PostParameterType_stuff<PostParameterType> {
    static TYPE = new _PostParameterType_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameterType;
