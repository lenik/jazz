
import { * as validators } from "./PersonValidators";
import type { _PostCategory_stuff } from "./_PostCategory_stuff";

export class PostCategory extends _PostCategory_stuff {
    static TYPE = new PostCategoryType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
