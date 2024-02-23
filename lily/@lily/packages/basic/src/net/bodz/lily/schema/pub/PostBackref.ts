import _PostBackref_stuff from "./_PostBackref_stuff";
import { _PostBackref_stuff_Type } from "./_PostBackref_stuff_Type";

export class PostBackref extends _PostBackref_stuff {
    static TYPE = new _PostBackref_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostBackref;
