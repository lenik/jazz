
import { * as validators } from "./PersonValidators";
import type { _PersonTag_stuff } from "./_PersonTag_stuff";

export class PersonTag extends _PersonTag_stuff {
    static TYPE = new PersonTagType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
