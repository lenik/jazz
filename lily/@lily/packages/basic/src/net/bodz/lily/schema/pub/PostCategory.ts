import PostCategoryTypeInfo from "./PostCategoryTypeInfo";
import _PostCategory_stuff from "./_PostCategory_stuff";

export class PostCategory extends _PostCategory_stuff<PostCategory> {

    static _typeInfo: PostCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PostCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostCategory;
