<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer, long } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { ArticleTalkVote } from "./ArticleTalkVote";

export const title = "Choose dialog for: Article talk vote";
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ArticleTalkVote.TYPE" :modal="modal">
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
