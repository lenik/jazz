<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer, long } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { VApi } from "./VApi";

export const title = "Choose dialog for: V api";
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

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="VApi.TYPE" :modal="modal">
        <th data-type="long" data-field="id">Id</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="any" data-field="properties">Properties</th>
        <th data-type="string" data-format="label" data-field="app">App</th>
        <th data-type="string" data-format="label" data-field="api">Api</th>
        <th data-type="string" data-field="callback">Callback</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
