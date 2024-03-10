import type { int } from "@skeljs/core/src/lang/basetype";

import CoParameter from "../../concrete/CoParameter";

export class _PostParameterType_stuff<this_t> extends CoParameter<this_t> {

    name?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PostParameterType_stuff;
