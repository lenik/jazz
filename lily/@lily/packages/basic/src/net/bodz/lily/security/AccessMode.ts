import Predef from "@skeljs/core/src/lang/Predef";
import PredefType from "@skeljs/core/src/lang/PredefType";
import type { int } from "@skeljs/core/src/lang/basetype";

export class AccessMode extends Predef<int> {

    static _typeInfo: AccessModeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new AccessModeTypeInfo();
        return this._typeInfo;
    }

    constructor(key: int, name: string, label?: string, icon?: string, description?: string) {
        super(key, name, label, icon, description);
    }

    static _400 = new AccessMode(256, "400");
    static _440 = new AccessMode(288, "440");
    static _444 = new AccessMode(292, "444");
    static _600 = new AccessMode(384, "600");
    static _640 = new AccessMode(416, "640");
    static _644 = new AccessMode(420, "644");
    static _660 = new AccessMode(432, "660");
    static _664 = new AccessMode(436, "664");
    static _666 = new AccessMode(438, "666");
}

export class AccessModeTypeInfo extends PredefType<AccessMode, int> {
    constructor() {
        super(AccessMode);
    }

}
export default AccessMode;
