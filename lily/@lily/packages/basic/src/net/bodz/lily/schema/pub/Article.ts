
import { * as validators } from "./PersonValidators";
import type { _Article_stuff } from "./_Article_stuff";

export class Article extends _Article_stuff {
    static TYPE = new ArticleType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
