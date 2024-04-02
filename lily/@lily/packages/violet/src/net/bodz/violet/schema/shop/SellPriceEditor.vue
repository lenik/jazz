<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import SellPrice from "./SellPrice";
import { _SellPrice_stuff_TYPE } from "./_SellPrice_stuff_TypeInfo";

export const title = "Editor view of: Sell price";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import ArtifactChooseDialog from "../art/ArtifactChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<SellPrice>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = SellPrice.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
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
        <FieldGroup :type="_SellPrice_stuff_TYPE">
            <FieldRow :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow :property="meta.price" v-model="model.price">
                <input type="number" v-model="model.price" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactChooseDialog ref="artifactChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
