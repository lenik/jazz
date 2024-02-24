import PostCategoryTypeInfo from "./PostCategoryTypeInfo";
import _PostCategory_stuff from "./_PostCategory_stuff";

export class PostCategory extends _PostCategory_stuff {
    static TYPE = new PostCategoryTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostCategory;
