import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ParameterDef from "./ParameterDef";
import _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class _ParameterValue_stuff extends CoEntity<integer> {
    static TYPE = new _ParameterValue_stuff_TypeInfo();

    id: integer;
    code?: string;
    val: string;

    parameter: ParameterDef;
    parameterId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ParameterValue_stuff;
