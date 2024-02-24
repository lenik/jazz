import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class _Storage_stuff extends CoEntity<integer> {
    static TYPE = new _Storage_stuff_TypeInfo();

    id: integer;
    name: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Storage_stuff;
