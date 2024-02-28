import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ParameterDef from "./ParameterDef";
import _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class _ParameterValue_stuff extends CoEntity<int> {
    static _typeInfo: _ParameterValue_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ParameterValue_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    val: string;

    parameter: ParameterDef;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ParameterValue_stuff;
