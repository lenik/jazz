import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ApiType from "./ApiType";
import VApp from "./VApp";
import _VApiLog_stuff_Type from "./_VApiLog_stuff_Type";

export class _VApiLog_stuff extends CoEntity<long> {
    static TYPE = new _VApiLog_stuff_Type();

    id: long;
    message?: string;
    err?: string;

    api?: ApiType;
    apiId?: integer;

    app: VApp;
    appId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VApiLog_stuff;
