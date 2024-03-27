import ArticleTagTypeInfo from "./ArticleTagTypeInfo";
import _ArticleTag_stuff from "./_ArticleTag_stuff";

export class ArticleTag extends _ArticleTag_stuff {

    static _typeInfo: ArticleTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArticleTag;
