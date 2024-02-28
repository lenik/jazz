import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import CoEntity from "../../concrete/CoEntity";
import type User from "../account/User";
import type FormDef from "../meta/FormDef";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class _VAppRequest_stuff extends CoEntity<int> {
    static _typeInfo: _VAppRequest_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _VAppRequest_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: int;
    subject: string;
    rawText?: string;
    formArguments?: string;
    dummy?: int;

    op?: User;
    opId?: int;

    form?: FormDef;
    formId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppRequest_stuff;
