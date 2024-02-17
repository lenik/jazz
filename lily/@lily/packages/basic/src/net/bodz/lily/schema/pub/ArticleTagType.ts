
import { * as validators } from "./PersonValidators";
import type { _ArticleTagType_stuff } from "./_ArticleTagType_stuff";

export class ArticleTagType extends _ArticleTagType_stuff<ArticleTagType> {
    static TYPE = new ArticleTagTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
