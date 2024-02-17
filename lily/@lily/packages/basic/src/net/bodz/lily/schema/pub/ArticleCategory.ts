
import { * as validators } from "./PersonValidators";
import type { _ArticleCategory_stuff } from "./_ArticleCategory_stuff";

export class ArticleCategory extends _ArticleCategory_stuff {
    static TYPE = new ArticleCategoryType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
