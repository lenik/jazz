<script lang="ts">

import { onMounted } from "vue";

import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

export interface Props {
    modal?: boolean | string
}
</script>

<script setup lang="ts">
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

%s(() => {
onMounted
});
</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="Person.TYPE" :modal="modal">
        <th data-type="long" data-field="id">Id</th>
        <th data-type="string" data-format="label" data-field="parent">Parent</th>
        <th data-type="string" data-format="label" data-field="user">User</th>
        <th data-type="integer" data-field="voteScore">Vote Score</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
