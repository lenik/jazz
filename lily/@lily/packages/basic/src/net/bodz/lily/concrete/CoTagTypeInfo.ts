import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoTagValidators from './CoTagValidators';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';

export class CoTagTypeInfo extends CoCodeTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoTagType"; }
    get icon() { return "fa-tag"; }
    get label() { return "Concrete Tag Type"; }
    get description() { return "Definition of a tag type."; }

    validators = new CoTagValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(selfType, idType);
        this.declare(this.declaredProperty);
    }

}

export default CoTagTypeInfo;