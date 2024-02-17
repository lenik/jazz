
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { ParameterDef } from "./ParameterDef";
import type { _ParameterValue_stuff_Type } from "./_ParameterValue_stuff_Type";

export class _ParameterValue_stuff extends CoEntity<Integer> {
    static TYPE = new _ParameterValue_stuff_Type();

    id: int;
    code?: string;
    val: string;

    parameter: ParameterDef;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}
