<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "skel01-core/src/lang/baseinfo";

import { User_TYPE } from "../account/UserTypeInfo";
import PostFav from "./PostFav";
import { Post_TYPE } from "./PostTypeInfo";

export const title = "Admin view of: Post fav";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import PostFavEditor from "./PostFavEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PostFav.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "Post": Post_TYPE,
    "User": User_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="Post" data-format="label" data-field="post">Post</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
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
.lily-admin {
    padding: 0;
}
</style>
