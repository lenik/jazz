import type { int } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type FormDef from "./FormDef";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class _FormParameter_stuff extends IdEntity<int> {

    static _typeInfo: _FormParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FormParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    name?: string;
    value?: string;

    form: FormDef;
    formId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FormParameter_stuff;
