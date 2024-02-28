<script lang="ts">
import { onMounted, ref } from "vue";

import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Object from "../../../../../java/lang/Object";
import User from "../account/User";
import Post from "./Post";
import { PostFav } from "./PostFav";

export const title = "Choose dialog for: Post fav";
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
    "Post": Post.TYPE,
    "User": User.TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PostFav.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="Object" data-field="id">Id</th>
        <th data-type="Post" data-format="label" data-field="post">Post</th>
        <th data-type="User" data-format="label" data-field="user">User</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
