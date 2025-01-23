<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { DOUBLE, INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";

import { UomRow } from "./UomRow";
import { UomRow_TYPE } from "./UomRowTypeInfo";

export const title = "Choose dialog for: Uom row";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "skel01-dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "JSON_VARIANT": JSON_VARIANT,
    "UomRow": UomRow_TYPE,
    "DOUBLE": DOUBLE,
};

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="UomRow.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="STRING" data-field="code">Code</th>
        <th data-type="STRING" data-field="label">Label</th>
        <th data-type="STRING" data-field="description">Description</th>
        <th data-type="STRING" data-field="icon">Icon</th>
        <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th class="hidden" data-type="JSON_VARIANT" data-field="files">Files</th>
        <th data-type="STRING" data-field="property">Property</th>
        <th data-type="UomRow" data-format="label" data-field="standard">Standard</th>
        <th data-type="DOUBLE" data-field="scale">Scale</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
