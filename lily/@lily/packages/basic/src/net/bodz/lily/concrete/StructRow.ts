import type { int } from 'skel01-core/src/lang/basetype';
import StructRowTypeInfo from './StructRowTypeInfo';
import { OffsetDateTime } from "skel01-core/src/lang/time";

export class StructRow {

    static _typeInfo: StructRowTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StructRowTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    // _class: StructRowTypeInfo;

    getClass(): StructRowTypeInfo {
        return this._getClass();
    }

    protected _getClass(): StructRowTypeInfo {
        // return this._class;
        return (this.constructor as any).TYPE;
    }

    get className() {
        return this._getClass().name;
    }
    // content

    creationDate: OffsetDateTime = OffsetDateTime.now()
    lastModified: OffsetDateTime = OffsetDateTime.now()
    version: int = 0

    constructor(o?: any) {
        if (o != null)
            Object.assign(this, o);
        // this._class = (this.constructor as any).TYPE;
    }
}

export default StructRow;
