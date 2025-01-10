import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoRelationValidators from './CoRelationValidators';
import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { LONG } from 'skel01-core/src/lang/baseinfo';

export class CoRelationTypeInfo extends IdEntityTypeInfo {

    readonly validators = new CoRelationValidators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.concrete.CoRelation"; }
    get icon() { return "fa-leaf"; }
    get label() { return "Relation"; }
    get description() { return "Relation def."; }

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: 'number', precision: 20, })
        });
    }

    static readonly INSTANCE = new CoRelationTypeInfo();
    
}

export default CoRelationTypeInfo;
export const CoRelation_TYPE = CoRelationTypeInfo.INSTANCE;
