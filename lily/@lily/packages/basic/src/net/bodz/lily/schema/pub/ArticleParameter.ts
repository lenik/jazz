import ArticleParameterTypeInfo from "./ArticleParameterTypeInfo";
import _ArticleParameter_stuff from "./_ArticleParameter_stuff";

export class ArticleParameter extends _ArticleParameter_stuff {

    static _typeInfo: ArticleParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleParameter;
