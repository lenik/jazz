import type { int } from "@skeljs/core/src/lang/basetype";

import CoParameter from "../../concrete/CoParameter";
import _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

export class _ArticleParameterType_stuff<this_t> extends CoParameter<this_t> {
    static _typeInfo: _ArticleParameterType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ArticleParameterType_stuff_TypeInfo();
        return this._typeInfo;
    }

    name?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleParameterType_stuff;
