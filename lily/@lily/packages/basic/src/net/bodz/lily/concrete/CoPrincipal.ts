import { int } from '@skeljs/core/src/lang/basetype';
import CoImaged from './CoImaged';
import CoPrincipalTypeInfo from './CoPrincipalTypeInfo';

export abstract class CoPrincipal extends CoImaged<int> {

    static readonly TYPE = CoPrincipalTypeInfo.INSTANCE;

    name?: string
    properties: any

    get uniqueName() { return this.name; }
    set uniqueName(val: string | undefined) { this.name = val; }

    constructor(o: any) {
        super(o);
    }
}

export default CoPrincipal;