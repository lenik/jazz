import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoTagValidators from './CoTagValidators';

export class CoTagTypeInfo extends CoCodeTypeInfo {

    name = "net.bodz.lily.concrete.CoTagType"
    icon = "fa-tag"
    label = "Concrete Tag Type"
    description = "Definition of a tag type."

    validators = new CoTagValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoTagTypeInfo;