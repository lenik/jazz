<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import StoreOrder from "./StoreOrder";
import { StoreOrder_TYPE } from "./StoreOrderTypeInfo";
import { _StoreOrder_stuff_TYPE } from "./_StoreOrder_stuff_TypeInfo";

export const title = "Editor view of: Store order";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import UserChooseDialog from "lily-basic/src/net/bodz/lily/schema/account/UserChooseDialog.vue";
import OrgUnitChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/OrgUnitChooseDialog.vue";
import OrganizationChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";
import PersonChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";
import FormDefChooseDialog from "lily-basic/src/net/bodz/lily/schema/meta/FormDefChooseDialog.vue";

import PlanChooseDialog from "../plan/PlanChooseDialog.vue";
import StoreCategoryChooseDialog from "./StoreCategoryChooseDialog.vue";
import StoreOrderChooseDialog from "./StoreOrderChooseDialog.vue";
import StorePhaseChooseDialog from "./StorePhaseChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<StoreOrder>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = StoreOrder.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();
const storeCategoryChooseDialog = ref<InstanceType<typeof StoreCategoryChooseDialog>>();
const storePhaseChooseDialog = ref<InstanceType<typeof StorePhaseChooseDialog>>();
const storeOrderChooseDialog = ref<InstanceType<typeof StoreOrderChooseDialog>>();
const planChooseDialog = ref<InstanceType<typeof PlanChooseDialog>>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
const orgUnitChooseDialog = ref<InstanceType<typeof OrgUnitChooseDialog>>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_StoreOrder_stuff_TYPE">
            <FieldRow :property="meta.beginTime" v-model="model.beginTime">
                <DateTime v-model="model.beginTime" />
            </FieldRow>
            <FieldRow :property="meta.endTime" v-model="model.endTime">
                <DateTime v-model="model.endTime" />
            </FieldRow>
            <FieldRow :property="meta.year" v-model="model.year">
                <input type="number" v-model="model.year" />
            </FieldRow>
            <FieldRow :property="meta.subject" v-model="model.subject">
                <input type="text" v-model="model.subject" />
            </FieldRow>
            <FieldRow :property="meta.rawText" v-model="model.rawText">
                <input type="text" v-model="model.rawText" />
            </FieldRow>
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.op" v-model="model.op">
                <RefEditor :dialog="userChooseDialog" v-model="model.op" v-model:id="model.opId" />
            </FieldRow>
            <FieldRow :property="meta.form" v-model="model.form">
                <RefEditor :dialog="formDefChooseDialog" v-model="model.form" v-model:id="model.formId" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="storeCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.phase" v-model="model.phase">
                <RefEditor :dialog="storePhaseChooseDialog" v-model="model.phase" v-model:id="model.phaseId" />
            </FieldRow>
            <FieldRow :property="meta.prev" v-model="model.prev">
                <RefEditor :dialog="storeOrderChooseDialog" v-model="model.prev" v-model:id="model.prevId" />
            </FieldRow>
            <FieldRow :property="meta.plan" v-model="model.plan">
                <RefEditor :dialog="planChooseDialog" v-model="model.plan" v-model:id="model.planId" />
            </FieldRow>
            <FieldRow :property="meta.org" v-model="model.org">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.org" v-model:id="model.orgId" />
            </FieldRow>
            <FieldRow :property="meta.orgUnit" v-model="model.orgUnit">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.orgUnit" v-model:id="model.orgUnitId" />
            </FieldRow>
            <FieldRow :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.person" v-model:id="model.personId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="StoreOrder_TYPE">
            <FieldRow :property="meta.length" v-model="model.length">
                <input type="number" v-model="model.length" />
            </FieldRow>
            <FieldRow :property="meta.totalQuantity" v-model="model.totalQuantity">
                <input type="number" v-model="model.totalQuantity" />
            </FieldRow>
            <FieldRow :property="meta.totalAmount" v-model="model.totalAmount">
                <input type="number" v-model="model.totalAmount" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <FormDefChooseDialog ref="formDefChooseDialog" />
    <StoreCategoryChooseDialog ref="storeCategoryChooseDialog" />
    <StorePhaseChooseDialog ref="storePhaseChooseDialog" />
    <StoreOrderChooseDialog ref="storeOrderChooseDialog" />
    <PlanChooseDialog ref="planChooseDialog" />
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <OrgUnitChooseDialog ref="orgUnitChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
