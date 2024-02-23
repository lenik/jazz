import _PostBackref_stuff from "./_PostBackref_stuff";
import { _PostBackref_stuffTypeInfo } from "./_PostBackref_stuffTypeInfo";

export class PostBackref extends _PostBackref_stuff {
    static TYPE = new _PostBackref_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostBackref;
