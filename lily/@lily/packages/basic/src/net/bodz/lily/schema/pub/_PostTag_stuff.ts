import type { int, long } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type Post from "./Post";
import type PostTagType from "./PostTagType";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class _PostTag_stuff extends IdEntity<int> {

    static _typeInfo: _PostTag_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PostTag_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    tag: PostTagType;
    tagId: int;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTag_stuff;
