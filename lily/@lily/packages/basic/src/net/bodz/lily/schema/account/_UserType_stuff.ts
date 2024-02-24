import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class _UserType_stuff extends CoEntity<integer> {
    static TYPE = new _UserType_stuff_TypeInfo();

    id: integer;
    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserType_stuff;
