import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoNodeTypeInfo from './CoNodeTypeInfo';
import CoCategoryValidators from './CoCategoryValidators';
import { UNDEFINED } from 'skel01-core/src/lang/baseinfo';

export class CoCategoryTypeInfo extends CoNodeTypeInfo {

    readonly validators = new CoCategoryValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoCategory"; }
    get icon() { return "fa-crow"; }
    get label() { return "Concrete Category"; }
    get description() { return "A category is a classification or grouping of similar items or concepts. It helps organize information and make it easier to find and understand. Categories can be broad or specific, depending on the context in which they are used. In terms of search engines, categories are often used to group search results into related topics or themes to help users find what they are looking for more easily."; }

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: 'number', precision: 20, })
        });
    }

    static readonly INSTANCE = new CoCategoryTypeInfo(UNDEFINED);

}

export default CoCategoryTypeInfo;
export const CoCategory_TYPE = CoCategoryTypeInfo.INSTANCE;
