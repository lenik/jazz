import type { int } from "@skeljs/core/src/lang/basetype";

import AbstractDefinition from "./AbstractDefinition";
import type ParameterDef from "./ParameterDef";

export class _ParameterValue_stuff<this_t> extends AbstractDefinition<this_t> {

    val: string;

    parameter: ParameterDef;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ParameterValue_stuff;
