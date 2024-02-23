import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ApiType from "./ApiType";
import VApp from "./VApp";
import _VApiCredit_stuff_Type from "./_VApiCredit_stuff_Type";

export class _VApiCredit_stuff extends CoEntity<integer> {
    static TYPE = new _VApiCredit_stuff_Type();

    id: integer;
    credit: BigInteger;

    api: ApiType;
    apiId: integer;

    app: VApp;
    appId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _VApiCredit_stuff;
