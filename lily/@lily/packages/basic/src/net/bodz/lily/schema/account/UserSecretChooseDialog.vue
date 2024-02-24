<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { UserSecret } from "./UserSecret";

export const title = "Choose dialog for: User secret";
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
    <EntityChooseDialog ref="entityChooseDialog" :type="UserSecret.TYPE" :modal="modal">
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="any" data-field="properties">Properties</th>
        <th data-type="string" data-format="label" data-field="user">The declaring user</th>
        <th data-type="string" data-field="password">Password data</th>
        <th data-type="string" data-field="question">Protection question</th>
        <th data-type="string" data-field="answer">Protection answer</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
