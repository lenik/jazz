<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import SalesOrderItem from "./SalesOrderItem";
import _SalesOrderItem_stuff from "./_SalesOrderItem_stuff";

export const title = "Editor view of: Sales order item";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoImagedEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";

import ArtifactChooseDialog from "../art/ArtifactChooseDialog.vue";
import SalesOrderChooseDialog from "./SalesOrderChooseDialog.vue";
import ShopItemChooseDialog from "./ShopItemChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<SalesOrderItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = SalesOrderItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const salesOrderChooseDialog = ref<InstanceType<typeof SalesOrderChooseDialog>>();
const shopItemChooseDialog = ref<InstanceType<typeof ShopItemChooseDialog>>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoImagedEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_SalesOrderItem_stuff.TYPE">
            <FieldRow :property="meta.batch" v-model="model.batch">
                <JsonEditor v-model="model.batch" />
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
            <FieldRow :property="meta.n1" v-model="model.n1">
                <input type="number" v-model="model.n1" />
            </FieldRow>
            <FieldRow :property="meta.order" v-model="model.order">
                <RefEditor :dialog="salesOrderChooseDialog" v-model="model.order" v-model:id="model.orderId" />
            </FieldRow>
            <FieldRow :property="meta.shopItem" v-model="model.shopItem">
                <RefEditor :dialog="shopItemChooseDialog" v-model="model.shopItem" v-model:id="model.shopItemId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <SalesOrderChooseDialog ref="salesOrderChooseDialog" />
    <ShopItemChooseDialog ref="shopItemChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
