import PostBackrefTypeInfo from "./PostBackrefTypeInfo";
import _PostBackref_stuff from "./_PostBackref_stuff";

export class PostBackref extends _PostBackref_stuff {
    static TYPE = new PostBackrefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostBackref;
