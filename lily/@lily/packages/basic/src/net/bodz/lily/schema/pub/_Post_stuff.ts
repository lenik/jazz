import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int, long } from "@skeljs/core/src/lang/basetype";

import CoMessage from "../../concrete/CoMessage";
import type Post from "./Post";
import type PostCategory from "./PostCategory";
import _Post_stuff_TypeInfo from "./_Post_stuff_TypeInfo";

export class _Post_stuff extends CoMessage<long> {

    static _typeInfo: _Post_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Post_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    favCount: int;
    voteCount: int;
    hateCount: int;
    messageCount: int;
    plugins?: JsonVariant;

    parent?: Post;
    parentId?: long;

    category?: PostCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Post_stuff;
