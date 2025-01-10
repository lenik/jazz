import { int } from 'skel01-core/src/lang/basetype';
import CoImaged from './CoImaged';
import CoPrincipalTypeInfo from './CoPrincipalTypeInfo';

export abstract class CoPrincipal extends CoImaged<int> {

    static _typeInfo: CoPrincipalTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoPrincipalTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    name?: string
    properties: any

    get uniqueName() { return this.name; }
    set uniqueName(val: string | undefined) { this.name = val; }

    constructor(o: any) {
        super(o);
    }
}

export default CoPrincipal;