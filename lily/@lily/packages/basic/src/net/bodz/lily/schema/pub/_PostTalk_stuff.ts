import type { long } from "@skeljs/core/src/lang/basetype";

import CoTalk from "../../concrete/CoTalk";
import type Post from "./Post";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class _PostTalk_stuff<this_t> extends CoTalk<this_t> {
    static _typeInfo: _PostTalk_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PostTalk_stuff_TypeInfo();
        return this._typeInfo;
    }

    formArguments?: string;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTalk_stuff;
