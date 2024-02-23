import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import VAppCat from "./VAppCat";
import VAppRequest from "./VAppRequest";
import _VApp_stuff_Type from "./_VApp_stuff_Type";

export class _VApp_stuff extends CoEntity<integer> {
    static TYPE = new _VApp_stuff_Type();

    id: integer;
    code?: string;
    secret: string;

    category?: VAppCat;
    categoryId?: integer;

    req?: VAppRequest;
    reqId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VApp_stuff;
