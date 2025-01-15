<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import StoreOrderItem from "./StoreOrderItem";
import { _StoreOrderItem_stuff_TYPE } from "./_StoreOrderItem_stuff_TypeInfo";

export const title = "Editor view of: Store order item";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";

import ArtifactChooseDialog from "../art/ArtifactChooseDialog.vue";
import RegionChooseDialog from "./RegionChooseDialog.vue";
import StoreOrderChooseDialog from "./StoreOrderChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<StoreOrderItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = StoreOrderItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const storeOrderChooseDialog = ref<InstanceType<typeof StoreOrderChooseDialog>>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
const regionChooseDialog = ref<InstanceType<typeof RegionChooseDialog>>();
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
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_StoreOrderItem_stuff_TYPE">
            <FieldRow :property="meta.batch" v-model="model.batch">
                <JsonEditor v-model="model.batch" />
            </FieldRow>
            <FieldRow :property="meta.serial" v-model="model.serial">
                <input type="number" v-model="model.serial" />
            </FieldRow>
            <FieldRow :property="meta.expire" v-model="model.expire">
                <DateTime v-model="model.expire" />
            </FieldRow>
            <FieldRow :property="meta.quantity" v-model="model.quantity">
                <input type="number" v-model="model.quantity" />
            </FieldRow>
            <FieldRow :property="meta.price" v-model="model.price">
                <input type="number" v-model="model.price" />
            </FieldRow>
            <FieldRow :property="meta.amount" v-model="model.amount">
                <input type="number" v-model="model.amount" />
            </FieldRow>
            <FieldRow :property="meta.notes" v-model="model.notes">
                <input type="text" v-model="model.notes" />
            </FieldRow>
            <FieldRow :property="meta.order" v-model="model.order">
                <RefEditor :dialog="storeOrderChooseDialog" v-model="model.order" v-model:id="model.orderId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
            <FieldRow :property="meta.region" v-model="model.region">
                <RefEditor :dialog="regionChooseDialog" v-model="model.region" v-model:id="model.regionId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <StoreOrderChooseDialog ref="storeOrderChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
    <RegionChooseDialog ref="regionChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
