import type { long } from "@skeljs/core/src/lang/basetype";

import FavRecord from "../../concrete/FavRecord";
import type Post from "./Post";
import _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

export class _PostFav_stuff extends FavRecord {
    static _typeInfo: _PostFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PostFav_stuff_TypeInfo();
        return this._typeInfo;
    }


    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostFav_stuff;
