
import { * as validators } from "./PersonValidators";
import type { _ArticleParameterType_stuff } from "./_ArticleParameterType_stuff";

export class ArticleParameterType extends _ArticleParameterType_stuff<ArticleParameterType> {
    static TYPE = new ArticleParameterTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
