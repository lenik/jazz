import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { INT, UNDEFINED } from 'skel01-core/src/lang/baseinfo';
import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import IdEntityValidators from './IdEntityValidators';

export class IdEntityTypeInfo extends CoObjectTypeInfo {

    readonly validators = new IdEntityValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.IdEntity"; }
    get icon() { return "fa-key"; }
    get label() { return "Concrete IdEntity"; }
    get description() { return "An entity has an id column."; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: this.idType, precision: 20, })
        });
    }

    static readonly INSTANCE = new IdEntityTypeInfo(UNDEFINED);

}

export default IdEntityTypeInfo;
export const IdEntity_TYPE = IdEntityTypeInfo.INSTANCE;
