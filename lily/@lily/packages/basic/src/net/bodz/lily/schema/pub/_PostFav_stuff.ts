import type { long } from "@skeljs/core/src/lang/type";

import FavRecord from "../../concrete/FavRecord";
import type Post from "./Post";
import _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

export class _PostFav_stuff extends FavRecord {
    static TYPE = new _PostFav_stuff_TypeInfo();


    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostFav_stuff;
