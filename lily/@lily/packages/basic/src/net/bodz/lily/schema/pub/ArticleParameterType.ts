import ArticleParameterTypeTypeInfo from "./ArticleParameterTypeTypeInfo";
import _ArticleParameterType_stuff from "./_ArticleParameterType_stuff";

export class ArticleParameterType extends _ArticleParameterType_stuff<ArticleParameterType> {
    static _typeInfo: ArticleParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ArticleParameterTypeTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleParameterType;
