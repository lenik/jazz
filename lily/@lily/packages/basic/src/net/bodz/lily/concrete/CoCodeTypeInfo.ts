
import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoCodeValidators from './CoCodeValidators';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export class CoCodeTypeInfo extends CoNodeTypeInfo {

    name = "net.bodz.lily.concrete.CoCode"
    icon = "far-hashtag"
    label = "Concrete Coded Entity"
    description = "This entity has a unique but optinoal code defined, so the object can be referred to by code."

    validators = new CoCodeValidators(this);

    declaredProperty: EntityPropertyMap = {
        code: property({
            type: 'string', precision: 30,
            validator: this.validators.validateCode
        })
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoCodeTypeInfo;