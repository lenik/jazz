import PostFavTypeInfo from "./PostFavTypeInfo";
import _PostFav_stuff from "./_PostFav_stuff";

export class PostFav extends _PostFav_stuff {
    static _typeInfo: PostFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PostFavTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PostFav;
