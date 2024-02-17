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
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="string" data-field="label">Label</th>
        <th data-type="string" data-field="description">Description</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="net.bodz.bas.repr.state.State" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="string" data-field="expr">Expr</th>
        <th data-type="integer" data-field="val">Val</th>
        <th data-type="[I" data-field="levels">Levels</th>
        <th data-type="boolean" data-field="descend">Descend</th>
        <th data-type="boolean" data-field="transient_">Transient_</th>
        <th data-type="boolean" data-field="indexed">Indexed</th>
        <th data-type="string" data-field="image">Image</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
