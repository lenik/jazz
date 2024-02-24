import type { integer, long } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type Post from "./Post";
import type PostTagType from "./PostTagType";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class _PostTag_stuff extends CoEntity<integer> {
    static TYPE = new _PostTag_stuff_TypeInfo();

    id: integer;

    tag: PostTagType;
    tagId: integer;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTag_stuff;
