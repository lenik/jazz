
import type { Moment } from "moment";

import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { User } from "../account/User";
import type { FormDef } from "../meta/FormDef";
import type { _VAppRequest_stuff_Type } from "./_VAppRequest_stuff_Type";

export class _VAppRequest_stuff extends CoEntity<Integer> {
    static TYPE = new _VAppRequest_stuff_Type();

    id: int;
    code?: string;
    beginTime?: Moment;
    endTime?: Moment;
    year: int;
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
