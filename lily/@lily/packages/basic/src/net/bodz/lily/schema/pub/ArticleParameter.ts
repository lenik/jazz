
import { * as validators } from "./PersonValidators";
import type { _ArticleParameter_stuff } from "./_ArticleParameter_stuff";

export class ArticleParameter extends _ArticleParameter_stuff {
    static TYPE = new ArticleParameterType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
