import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type VAppCat from "./VAppCat";
import type VAppRequest from "./VAppRequest";
import _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class _VApp_stuff extends CoEntity<integer> {
    static TYPE = new _VApp_stuff_TypeInfo();

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
