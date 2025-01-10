<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import Organization from "./Organization";
import { _Organization_stuff_TYPE } from "./_Organization_stuff_TypeInfo";

export const title = "Editor view of: Organization";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import PartyFieldGroup from "./PartyFieldGroup.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Organization>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Organization.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
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
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <PartyFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Organization_stuff_TYPE">
            <FieldRow :property="meta.roleCount" v-model="model.roleCount">
                <input type="number" v-model="model.roleCount" />
            </FieldRow>
            <FieldRow :property="meta.bankCount" v-model="model.bankCount">
                <input type="number" v-model="model.bankCount" />
            </FieldRow>
            <FieldRow :property="meta.size" v-model="model.size">
                <input type="number" v-model="model.size" />
            </FieldRow>
            <FieldRow :property="meta.taxId" v-model="model.taxId">
                <input type="text" v-model="model.taxId" />
            </FieldRow>
        </FieldGroup>
    </div>
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
