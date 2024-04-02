<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import StoreItem from "./StoreItem";
import { _StoreItem_stuff_TYPE } from "./_StoreItem_stuff_TypeInfo";

export const title = "Editor view of: Store item";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import ArtifactChooseDialog from "../art/ArtifactChooseDialog.vue";
import RegionChooseDialog from "./RegionChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<StoreItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = StoreItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
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
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_StoreItem_stuff_TYPE">
            <FieldRow :property="meta.batch" v-model="model.batch">
                <JsonEditor v-model="model.batch" />
            </FieldRow>
            <FieldRow :property="meta.quantity" v-model="model.quantity">
                <input type="number" v-model="model.quantity" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
            <FieldRow :property="meta.region" v-model="model.region">
                <RefEditor :dialog="regionChooseDialog" v-model="model.region" v-model:id="model.regionId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactChooseDialog ref="artifactChooseDialog" />
    <RegionChooseDialog ref="regionChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
