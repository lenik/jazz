import type { integer } from "@skeljs/core/src/lang/type";

import CoParameter from "../../concrete/CoParameter";
import _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

export class _ArticleParameterType_stuff<this_t> extends CoParameter<this_t> {
    static TYPE = new _ArticleParameterType_stuff_TypeInfo();

    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleParameterType_stuff;
