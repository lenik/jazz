
import { * as validators } from "./PersonValidators";
import type { _ArticleTalk_stuff } from "./_ArticleTalk_stuff";

export class ArticleTalk extends _ArticleTalk_stuff<ArticleTalk> {
    static TYPE = new ArticleTalkType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
