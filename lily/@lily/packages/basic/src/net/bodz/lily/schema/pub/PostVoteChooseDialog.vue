<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";

import { User_TYPE } from "../account/UserTypeInfo";
import { Post_TYPE } from "./PostTypeInfo";
import { PostVote } from "./PostVote";

export const title = "Choose dialog for: Post vote";
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
    "LONG": LONG,
    "Post": Post_TYPE,
    "User": User_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PostVote.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="Post" data-format="label" data-field="parent">Parent</th>
        <th data-type="User" data-format="label" data-field="user">User</th>
        <th data-type="INT" data-field="voteScore">Vote Score</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
