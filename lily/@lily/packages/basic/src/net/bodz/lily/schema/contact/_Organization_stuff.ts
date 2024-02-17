
import type { Party } from "./Party";
import type { _Organization_stuff_Type } from "./_Organization_stuff_Type";

export class _Organization_stuff extends Party {
    static TYPE = new _Organization_stuff_Type();

    roleCount: int;
    bankCount: int;
    size: int;
    taxId?: string;

    constructor(o: any) {
        super(o);
    }
}
