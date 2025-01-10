<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import SalesOrder from "./SalesOrder";
import { SalesOrder_TYPE } from "./SalesOrderTypeInfo";
import { _SalesOrder_stuff_TYPE } from "./_SalesOrder_stuff_TypeInfo";

export const title = "Editor view of: Sales order";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoImagedEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventFieldGroup.vue";
import CoMessageFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import OrganizationChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";
import PersonChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";

import PlanChooseDialog from "../plan/PlanChooseDialog.vue";
import SalesCategoryChooseDialog from "./SalesCategoryChooseDialog.vue";
import SalesOrderChooseDialog from "./SalesOrderChooseDialog.vue";
import SalesPhaseChooseDialog from "./SalesPhaseChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<SalesOrder>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = SalesOrder.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const salesCategoryChooseDialog = ref<InstanceType<typeof SalesCategoryChooseDialog>>();
const salesPhaseChooseDialog = ref<InstanceType<typeof SalesPhaseChooseDialog>>();
const salesOrderChooseDialog = ref<InstanceType<typeof SalesOrderChooseDialog>>();
const planChooseDialog = ref<InstanceType<typeof PlanChooseDialog>>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
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
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoImagedEventFieldGroup :meta="meta" v-model="model" />
        <CoMessageFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_SalesOrder_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="salesCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.phase" v-model="model.phase">
                <RefEditor :dialog="salesPhaseChooseDialog" v-model="model.phase" v-model:id="model.phaseId" />
            </FieldRow>
            <FieldRow :property="meta.previousOrder" v-model="model.previousOrder">
                <RefEditor :dialog="salesOrderChooseDialog" v-model="model.previousOrder" v-model:id="model.previousOrderId" />
            </FieldRow>
            <FieldRow :property="meta.plan" v-model="model.plan">
                <RefEditor :dialog="planChooseDialog" v-model="model.plan" v-model:id="model.planId" />
            </FieldRow>
            <FieldRow :property="meta.customerOrg" v-model="model.customerOrg">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.customerOrg" v-model:id="model.customerOrgId" />
            </FieldRow>
            <FieldRow :property="meta.customer" v-model="model.customer">
                <RefEditor :dialog="personChooseDialog" v-model="model.customer" v-model:id="model.customerId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="SalesOrder_TYPE">
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
    <SalesCategoryChooseDialog ref="salesCategoryChooseDialog" />
    <SalesPhaseChooseDialog ref="salesPhaseChooseDialog" />
    <SalesOrderChooseDialog ref="salesOrderChooseDialog" />
    <PlanChooseDialog ref="planChooseDialog" />
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
