import { int } from 'skel01-core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export abstract class CoNode<This, Id> extends IdEntity<Id> {

    static _typeInfo: CoNodeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoNodeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    parent?: This
    private _parentId?: int
    get parentId() {
        return this.parent != null ? this.parent.id : this.parentId;
    }
    set parentId(val: int | undefined) {
        this.parentId = val;
    }

    children: This[]

    refCount?: int

    constructor(o: any) {
        super(o);
    }
}

export default CoNode;