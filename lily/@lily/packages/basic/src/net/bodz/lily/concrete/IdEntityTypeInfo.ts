import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT } from '@skeljs/core/src/lang/baseinfo';
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import IdEntityValidators from './IdEntityValidators';

export class IdEntityTypeInfo extends CoObjectTypeInfo {

    idType: TypeInfo<any>;

    get name() { return "net.bodz.lily.concrete.IdEntity"; }
    get icon() { return "fa-key"; }
    get label() { return "Concrete IdEntity"; }
    get description() { return "An entity has an id column."; }

    validators = new IdEntityValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: this.idType, precision: 20, })
        });
    }

    constructor(idType: TypeInfo<any>) {
        super();
        if (idType != null) { // compatible
            this.idType = idType;
        }
    }

}

export default IdEntityTypeInfo;