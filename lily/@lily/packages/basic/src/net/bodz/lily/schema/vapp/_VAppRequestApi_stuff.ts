
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Long } from "../../../../../java/lang/Long";
import type { ApiType } from "./ApiType";
import type { VAppRequest } from "./VAppRequest";
import type { _VAppRequestApi_stuff_Type } from "./_VAppRequestApi_stuff_Type";

export class _VAppRequestApi_stuff extends CoEntity<Long> {
    static TYPE = new _VAppRequestApi_stuff_Type();

    id: long;

    parent: VAppRequest;
    parentId: int;

    api: ApiType;
    apiId: int;

    constructor(o: any) {
        super(o);
    }
}
