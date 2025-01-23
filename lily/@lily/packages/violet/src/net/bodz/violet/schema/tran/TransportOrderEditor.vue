<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import TransportOrder from "./TransportOrder";
import { TransportOrder_TYPE } from "./TransportOrderTypeInfo";
import { _TransportOrder_stuff_TYPE } from "./_TransportOrder_stuff_TypeInfo";

export const title = "Editor view of: Transport order";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoImagedEventFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoImagedEventFieldGroup.vue";
import CoMessageFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import OrganizationChooseDialog from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";

import SalesOrderChooseDialog from "../shop/SalesOrderChooseDialog.vue";
import StoreOrderChooseDialog from "../store/StoreOrderChooseDialog.vue";
import TransportCategoryChooseDialog from "./TransportCategoryChooseDialog.vue";
import TransportOrderChooseDialog from "./TransportOrderChooseDialog.vue";
import TransportPhaseChooseDialog from "./TransportPhaseChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TransportOrder>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TransportOrder.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const transportCategoryChooseDialog = ref<InstanceType<typeof TransportCategoryChooseDialog>>();
const transportPhaseChooseDialog = ref<InstanceType<typeof TransportPhaseChooseDialog>>();
const transportOrderChooseDialog = ref<InstanceType<typeof TransportOrderChooseDialog>>();
const salesOrderChooseDialog = ref<InstanceType<typeof SalesOrderChooseDialog>>();
const storeOrderChooseDialog = ref<InstanceType<typeof StoreOrderChooseDialog>>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
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
        <FieldGroup :type="_TransportOrder_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
            <FieldRow :property="meta.shipcost" v-model="model.shipcost">
                <input type="number" v-model="model.shipcost" />
            </FieldRow>
            <FieldRow :property="meta.totalQuantity" v-model="model.totalQuantity">
                <input type="number" v-model="model.totalQuantity" />
            </FieldRow>
            <FieldRow :property="meta.totalAmount" v-model="model.totalAmount">
                <input type="number" v-model="model.totalAmount" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="transportCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.phase" v-model="model.phase">
                <RefEditor :dialog="transportPhaseChooseDialog" v-model="model.phase" v-model:id="model.phaseId" />
            </FieldRow>
            <FieldRow :property="meta.prev" v-model="model.prev">
                <RefEditor :dialog="transportOrderChooseDialog" v-model="model.prev" v-model:id="model.prevId" />
            </FieldRow>
            <FieldRow :property="meta.salesOrder" v-model="model.salesOrder">
                <RefEditor :dialog="salesOrderChooseDialog" v-model="model.salesOrder" v-model:id="model.salesOrderId" />
            </FieldRow>
            <FieldRow :property="meta.storeodr" v-model="model.storeodr">
                <RefEditor :dialog="storeOrderChooseDialog" v-model="model.storeodr" v-model:id="model.storeodrId" />
            </FieldRow>
            <FieldRow :property="meta.shipper" v-model="model.shipper">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.shipper" v-model:id="model.shipperId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="TransportOrder_TYPE">
            <FieldRow :property="meta.length" v-model="model.length">
                <input type="number" v-model="model.length" />
            </FieldRow>
        </FieldGroup>
    </div>
    <TransportCategoryChooseDialog ref="transportCategoryChooseDialog" />
    <TransportPhaseChooseDialog ref="transportPhaseChooseDialog" />
    <TransportOrderChooseDialog ref="transportOrderChooseDialog" />
    <SalesOrderChooseDialog ref="salesOrderChooseDialog" />
    <StoreOrderChooseDialog ref="storeOrderChooseDialog" />
    <OrganizationChooseDialog ref="organizationChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
