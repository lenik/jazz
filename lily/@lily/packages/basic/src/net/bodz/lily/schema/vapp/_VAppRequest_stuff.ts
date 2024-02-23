import { Moment } from "moment";

import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import User from "../account/User";
import FormDef from "../meta/FormDef";
import _VAppRequest_stuff_Type from "./_VAppRequest_stuff_Type";

export class _VAppRequest_stuff extends CoEntity<integer> {
    static TYPE = new _VAppRequest_stuff_Type();

    id: integer;
    code?: string;
    beginTime?: Moment;
    endTime?: Moment;
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
