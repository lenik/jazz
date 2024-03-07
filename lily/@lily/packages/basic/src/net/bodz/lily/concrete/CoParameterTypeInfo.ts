
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoParameterValidators from './CoParameterValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';

export class CoParameterTypeInfo extends CoCodeTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoParameter"; }
    get icon() { return "fa-adjust"; }
    get label() { return "Concrete Parameter"; }
    get description() { return "Definition of a parameter type."; }

    validators = new CoParameterValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(selfType, idType);
        this.declare(this.declaredProperty);
    }

}

export default CoParameterTypeInfo;