import DiaryCategoryTypeInfo from "./DiaryCategoryTypeInfo";
import _DiaryCategory_stuff from "./_DiaryCategory_stuff";

export class DiaryCategory extends _DiaryCategory_stuff<DiaryCategory> {

    static _typeInfo: DiaryCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default DiaryCategory;
