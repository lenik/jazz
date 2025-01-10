<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabOrderItem from "./FabOrderItem";
import { _FabOrderItem_stuff_TYPE } from "./_FabOrderItem_stuff_TypeInfo";

export const title = "Editor view of: Fab order item";
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
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import ArtifactChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactChooseDialog.vue";

import FabOrderChooseDialog from "./FabOrderChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabOrderItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabOrderItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabOrderChooseDialog = ref<InstanceType<typeof FabOrderChooseDialog>>();
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
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoImagedEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_FabOrderItem_stuff_TYPE">
            <FieldRow :property="meta.resale" v-model="model.resale">
                <input type="checkbox" v-model="model.resale" />
            </FieldRow>
            <FieldRow :property="meta.altLabel" v-model="model.altLabel">
                <input type="text" v-model="model.altLabel" />
            </FieldRow>
            <FieldRow :property="meta.altSpec" v-model="model.altSpec">
                <input type="text" v-model="model.altSpec" />
            </FieldRow>
            <FieldRow :property="meta.altUom" v-model="model.altUom">
                <input type="text" v-model="model.altUom" />
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
                <RefEditor :dialog="fabOrderChooseDialog" v-model="model.order" v-model:id="model.orderId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabOrderChooseDialog ref="fabOrderChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
