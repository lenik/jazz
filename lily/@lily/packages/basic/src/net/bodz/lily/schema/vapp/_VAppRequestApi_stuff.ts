import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ApiType from "./ApiType";
import VAppRequest from "./VAppRequest";
import _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class _VAppRequestApi_stuff extends CoEntity<long> {
    static TYPE = new _VAppRequestApi_stuff_TypeInfo();

    id: long;

    parent: VAppRequest;
    parentId: integer;

    api: ApiType;
    apiId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppRequestApi_stuff;
