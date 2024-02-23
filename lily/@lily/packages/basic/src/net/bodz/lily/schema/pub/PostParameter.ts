import _PostParameter_stuff from "./_PostParameter_stuff";
import { _PostParameter_stuff_Type } from "./_PostParameter_stuff_Type";

export class PostParameter extends _PostParameter_stuff {
    static TYPE = new _PostParameter_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostParameter;
