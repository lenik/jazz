
import type { CoParameter } from "@skeljs/dba/src/net/bodz/lily/concrete/CoParameter";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import type { _ArticleParameterType_stuff_Type } from "./_ArticleParameterType_stuff_Type";

export class _ArticleParameterType_stuff<this_t> extends CoParameter<this_t> {
    static TYPE = new _ArticleParameterType_stuff_Type();

    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}