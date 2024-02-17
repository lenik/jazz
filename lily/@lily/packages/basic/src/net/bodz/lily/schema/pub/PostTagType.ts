
import { * as validators } from "./PersonValidators";
import type { _PostTagType_stuff } from "./_PostTagType_stuff";

export class PostTagType extends _PostTagType_stuff<PostTagType> {
    static TYPE = new PostTagTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
