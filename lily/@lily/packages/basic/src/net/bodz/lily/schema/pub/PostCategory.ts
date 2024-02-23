import _PostCategory_stuff from "./_PostCategory_stuff";
import { _PostCategory_stuffTypeInfo } from "./_PostCategory_stuffTypeInfo";

export class PostCategory extends _PostCategory_stuff {
    static TYPE = new _PostCategory_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostCategory;
