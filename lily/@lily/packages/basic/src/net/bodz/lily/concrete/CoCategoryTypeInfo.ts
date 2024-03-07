
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import CoNodeTypeInfo from './CoNodeTypeInfo';
import CoCategoryValidators from './CoCategoryValidators';

export class CoCategoryTypeInfo extends CoNodeTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoCategory"; }
    get icon() { return "fa-crow"; }
    get label() { return "Concrete Category"; }
    get description() { return "A category is a classification or grouping of similar items or concepts. It helps organize information and make it easier to find and understand. Categories can be broad or specific, depending on the context in which they are used. In terms of search engines, categories are often used to group search results into related topics or themes to help users find what they are looking for more easily."; }

    validators = new CoCategoryValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoCategoryTypeInfo;