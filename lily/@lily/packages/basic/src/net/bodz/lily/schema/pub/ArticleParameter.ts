import ArticleParameterTypeInfo from "./ArticleParameterTypeInfo";
import _ArticleParameter_stuff from "./_ArticleParameter_stuff";

export class ArticleParameter extends _ArticleParameter_stuff {
    static TYPE = new ArticleParameterTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleParameter;
