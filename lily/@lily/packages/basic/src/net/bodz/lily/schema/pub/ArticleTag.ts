
import { * as validators } from "./PersonValidators";
import type { _ArticleTag_stuff } from "./_ArticleTag_stuff";

export class ArticleTag extends _ArticleTag_stuff {
    static TYPE = new ArticleTagType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
