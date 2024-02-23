import _PostCategory_stuff from "./_PostCategory_stuff";
import { _PostCategory_stuff_Type } from "./_PostCategory_stuff_Type";

export class PostCategory extends _PostCategory_stuff {
    static TYPE = new _PostCategory_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostCategory;
