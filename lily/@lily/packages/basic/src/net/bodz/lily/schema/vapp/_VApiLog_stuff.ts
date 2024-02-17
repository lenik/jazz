
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer, long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Long } from "../../../../../java/lang/Long";
import type { ApiType } from "./ApiType";
import type { VApp } from "./VApp";
import type { _VApiLog_stuff_Type } from "./_VApiLog_stuff_Type";

export class _VApiLog_stuff extends CoEntity<Long> {
    static TYPE = new _VApiLog_stuff_Type();

    id: long;
    message?: string;
    err?: string;

    api?: ApiType;
    apiId?: integer;

    app: VApp;
    appId: int;

    constructor(o: any) {
        super(o);
    }
}
