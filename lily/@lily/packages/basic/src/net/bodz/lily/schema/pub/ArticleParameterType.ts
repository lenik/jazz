import _ArticleParameterType_stuff from "./_ArticleParameterType_stuff";
import { _ArticleParameterType_stuffTypeInfo } from "./_ArticleParameterType_stuffTypeInfo";

export class ArticleParameterType extends _ArticleParameterType_stuff<ArticleParameterType> {
    static TYPE = new _ArticleParameterType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleParameterType;
