import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type User from "../account/User";
import type FormDef from "../meta/FormDef";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class _VAppRequest_stuff extends CoEntity<integer> {
    static TYPE = new _VAppRequest_stuff_TypeInfo();

    id: integer;
    code?: string;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: integer;
    subject: string;
    rawText?: string;
    formArguments?: string;
    dummy?: integer;

    op?: User;
    opId?: integer;

    form?: FormDef;
    formId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppRequest_stuff;
