
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { VAppCat } from "./VAppCat";
import type { VAppRequest } from "./VAppRequest";
import type { _VApp_stuff_Type } from "./_VApp_stuff_Type";

export class _VApp_stuff extends CoEntity<Integer> {
    static TYPE = new _VApp_stuff_Type();

    id: int;
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
