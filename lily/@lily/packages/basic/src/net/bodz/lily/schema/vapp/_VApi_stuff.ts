
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Long } from "../../../../../java/lang/Long";
import type { ApiType } from "./ApiType";
import type { VApp } from "./VApp";
import type { _VApi_stuff_Type } from "./_VApi_stuff_Type";

export class _VApi_stuff extends CoEntity<Long> {
    static TYPE = new _VApi_stuff_Type();

    id: long;
    callback?: string;

    api: ApiType;
    apiId: int;

    app: VApp;
    appId: int;

    constructor(o: any) {
        super(o);
    }
}
