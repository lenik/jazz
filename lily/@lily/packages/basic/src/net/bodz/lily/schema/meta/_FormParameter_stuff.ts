import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type FormDef from "./FormDef";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class _FormParameter_stuff extends CoEntity<int> {
    static _typeInfo: _FormParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _FormParameter_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    value?: string;

    form: FormDef;
    formId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FormParameter_stuff;
