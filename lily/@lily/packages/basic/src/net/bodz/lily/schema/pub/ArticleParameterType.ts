import ArticleParameterTypeTypeInfo from "./ArticleParameterTypeTypeInfo";
import _ArticleParameterType_stuff from "./_ArticleParameterType_stuff";

export class ArticleParameterType extends _ArticleParameterType_stuff<ArticleParameterType> {

    static _typeInfo: ArticleParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleParameterTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleParameterType;
