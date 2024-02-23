import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ApiType from "./ApiType";
import VApp from "./VApp";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class _VApi_stuff extends CoEntity<long> {
    static TYPE = new _VApi_stuff_TypeInfo();

    id: long;
    callback?: string;

    api: ApiType;
    apiId: integer;

    app: VApp;
    appId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VApi_stuff;
