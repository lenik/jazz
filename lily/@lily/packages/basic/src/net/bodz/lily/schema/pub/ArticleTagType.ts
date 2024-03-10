import ArticleTagTypeTypeInfo from "./ArticleTagTypeTypeInfo";
import _ArticleTagType_stuff from "./_ArticleTagType_stuff";

export class ArticleTagType extends _ArticleTagType_stuff<ArticleTagType> {

    static _typeInfo: ArticleTagTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleTagTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTagType;
