
import { * as validators } from "./PersonValidators";
import type { _PostBackref_stuff } from "./_PostBackref_stuff";

export class PostBackref extends _PostBackref_stuff {
    static TYPE = new PostBackrefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
