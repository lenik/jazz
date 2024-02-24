import ArticleParameterTypeTypeInfo from "./ArticleParameterTypeTypeInfo";
import _ArticleParameterType_stuff from "./_ArticleParameterType_stuff";

export class ArticleParameterType extends _ArticleParameterType_stuff<ArticleParameterType> {
    static TYPE = new ArticleParameterTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleParameterType;
