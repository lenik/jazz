import type { integer, long } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class _VApiLog_stuff extends CoEntity<long> {
    static TYPE = new _VApiLog_stuff_TypeInfo();

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
