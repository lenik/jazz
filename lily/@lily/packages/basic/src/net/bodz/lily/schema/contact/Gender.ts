import Predef from "@skeljs/core/src/lang/Predef";
import PredefType from "@skeljs/core/src/lang/PredefType";
import type { char } from "@skeljs/core/src/lang/basetype";

export class Gender extends Predef<char> {

    static _typeInfo: GenderTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new GenderTypeInfo();
        return this._typeInfo;
    }

    constructor(key: char, name: string, label?: string, icon?: string, description?: string) {
        super(key, name, label, icon, description);
    }

    static HRTF = new Gender('F', "HRT female");
    static HRTM = new Gender('M', "HRT male");
    static FEMALE = new Gender('f', "female");
    static INTERSEX = new Gender('i', "intersex");
    static MALE = new Gender('m', "male");
    static NEUTRAL = new Gender('n', "neutral");
    static OTHER = new Gender('o', "other");
    static QUEER = new Gender('q', "queer");
    static FTM = new Gender('x', "FtM");
    static MTF = new Gender('y', "MtF");
}

export class GenderTypeInfo extends PredefType<Gender, char> {
    constructor() {
        super(Gender);
    }

}
export default Gender;
