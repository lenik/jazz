import type { integer } from "@skeljs/core/src/lang/type";

import Party from "./Party";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class _Organization_stuff extends Party {
    static TYPE = new _Organization_stuff_TypeInfo();

    properties?: any;
    roleCount: integer;
    bankCount: integer;
    size: integer;
    taxId?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Organization_stuff;
