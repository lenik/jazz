
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { ApiType } from "./ApiType";
import type { VApp } from "./VApp";
import type { _VApiCredit_stuff_Type } from "./_VApiCredit_stuff_Type";

export class _VApiCredit_stuff extends CoEntity<Integer> {
    static TYPE = new _VApiCredit_stuff_Type();

    id: int;
    credit: BigInteger;

    api: ApiType;
    apiId: int;

    app: VApp;
    appId: int;

    constructor(o: any) {
        super(o);
    }
}
