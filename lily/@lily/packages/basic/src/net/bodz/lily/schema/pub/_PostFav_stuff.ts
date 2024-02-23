import type { long } from "@skeljs/core/src/lang/type";
import FavRecord from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecord";

import Post from "./Post";
import _PostFav_stuff_Type from "./_PostFav_stuff_Type";

export class _PostFav_stuff extends FavRecord {
    static TYPE = new _PostFav_stuff_Type();


    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostFav_stuff;
