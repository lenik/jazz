import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int, long } from "@skeljs/core/src/lang/basetype";

import CoMessage from "../../concrete/CoMessage";
import type ArticleCategory from "./ArticleCategory";
import _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

export class _Article_stuff extends CoMessage<long> {

    static _typeInfo: _Article_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Article_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    favCount: int;
    voteCount: int;
    hateCount: int;
    messageCount: int;
    plugins?: JsonVariant;

    category?: ArticleCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Article_stuff;
