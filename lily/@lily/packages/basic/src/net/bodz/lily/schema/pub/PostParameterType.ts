
import { * as validators } from "./PersonValidators";
import type { _PostParameterType_stuff } from "./_PostParameterType_stuff";

export class PostParameterType extends _PostParameterType_stuff<PostParameterType> {
    static TYPE = new PostParameterTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
