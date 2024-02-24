import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class _VApiCredit_stuff extends CoEntity<integer> {
    static TYPE = new _VApiCredit_stuff_TypeInfo();

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
