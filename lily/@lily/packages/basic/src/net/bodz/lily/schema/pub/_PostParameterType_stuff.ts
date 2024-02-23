import type { integer } from "@skeljs/core/src/lang/type";
import CoParameter from "@skeljs/dba/src/net/bodz/lily/concrete/CoParameter";

import { TypeParamType } from "../../meta/TypeParamType";
import _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

export class _PostParameterType_stuff<this_t> extends CoParameter<this_t> {
    static TYPE = new _PostParameterType_stuff_TypeInfo();

    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PostParameterType_stuff;
