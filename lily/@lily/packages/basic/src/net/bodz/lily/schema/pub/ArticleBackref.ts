
import { * as validators } from "./PersonValidators";
import type { _ArticleBackref_stuff } from "./_ArticleBackref_stuff";

export class ArticleBackref extends _ArticleBackref_stuff {
    static TYPE = new ArticleBackrefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
