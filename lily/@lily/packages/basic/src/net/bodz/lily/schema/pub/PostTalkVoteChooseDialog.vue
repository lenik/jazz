<script lang="ts">
import { onMounted, ref } from "vue";

import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Object from "../../../../../java/lang/Object";
import User from "../account/User";
import PostTalk from "./PostTalk";
import { PostTalkVote } from "./PostTalkVote";

export const title = "Choose dialog for: Post talk vote";
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
    "Object": Object.TYPE,
    "PostTalk": PostTalk.TYPE,
    "User": User.TYPE,
    "INT": INT,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PostTalkVote.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="Object" data-field="id">Id</th>
        <th data-type="PostTalk" data-format="label" data-field="parent">Parent</th>
        <th data-type="User" data-format="label" data-field="user">User</th>
        <th data-type="INT" data-field="voteScore">Vote Score</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
