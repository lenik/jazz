<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { FabStdTestParameter } from "./FabStdTestParameter";
import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";

export const title = "Choose dialog for: Fab std test parameter";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabStdTest": FabStdTest_TYPE,
    "BOOLEAN": BOOLEAN,
    "JSON_VARIANT": JSON_VARIANT,
    "STRING": STRING,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabStdTestParameter.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="FabStdTest" data-format="label" data-field="test">Test</th>
        <th data-type="BOOLEAN" data-field="required">Required</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="STRING" data-field="expected">Expected</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
