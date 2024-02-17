
import { * as validators } from "./PersonValidators";
import type { _PostParameter_stuff } from "./_PostParameter_stuff";

export class PostParameter extends _PostParameter_stuff {
    static TYPE = new PostParameterType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
