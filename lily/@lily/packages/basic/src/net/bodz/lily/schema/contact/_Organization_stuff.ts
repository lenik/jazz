import type { integer } from "@skeljs/core/src/lang/type";

import Party from "./Party";
import _Organization_stuff_Type from "./_Organization_stuff_Type";

export class _Organization_stuff extends Party {
    static TYPE = new _Organization_stuff_Type();

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
