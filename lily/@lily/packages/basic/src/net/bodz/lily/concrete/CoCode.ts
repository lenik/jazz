import { int } from '@skeljs/core/src/lang/basetype';
import CoNode from './CoNode';
import CoCodeTypeInfo from './CoCodeTypeInfo';

export abstract class CoCode<This> extends CoNode<This, int> {
    static TYPE = new CoCodeTypeInfo();

    code?: string

    get uniqueName() { return this.code; }
    set uniqueName(val: string | undefined) { this.code = val; }

    constructor(o: any) {
        super(o);
    }
}

export default CoCode;