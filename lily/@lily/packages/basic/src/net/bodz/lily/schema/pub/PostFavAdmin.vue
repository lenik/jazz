<script lang="ts">

import { onMounted, ref } from "vue";

import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import type { PostFav } from "./PostFav";
import PostFavEditor from "./PostFavEditor.vue";

export interface Props {
}
</script>

<script setup lang="ts">

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PostFav.TYPE;
const selection = ref<any>({});
onMounted(() => {
});

</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="long" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="post">Post</th>
            <th data-type="string" data-format="label" data-field="user">User</th>
        </template>
        <template #preview>
            <PostFavEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PostFavEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {}
}
</style>
