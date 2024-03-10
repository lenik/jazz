import DiaryTagTypeInfo from "./DiaryTagTypeInfo";
import _DiaryTag_stuff from "./_DiaryTag_stuff";

export class DiaryTag extends _DiaryTag_stuff<DiaryTag> {

    static _typeInfo: DiaryTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default DiaryTag;
