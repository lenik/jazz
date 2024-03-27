<script lang="ts">
import { onMounted, ref } from "vue";

import { DOUBLE, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import Post from "./Post";
import PostParameter from "./PostParameter";
import PostParameterType from "./PostParameterType";

export const title = "Admin view of: Post parameter";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PostParameterEditor from "./PostParameterEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PostParameter.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Post": Post.TYPE,
    "PostParameterType": PostParameterType.TYPE,
    "DOUBLE": DOUBLE,
    "STRING": STRING,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="Post" data-format="label" data-field="post">Post</th>
            <th data-type="PostParameterType" data-format="label" data-field="parameter">Parameter</th>
            <th data-type="INT" data-field="ival">Ival</th>
            <th data-type="DOUBLE" data-field="fval">Fval</th>
            <th data-type="STRING" data-field="sval">Sval</th>
        </template>
        <template #preview>
            <PostParameterEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PostParameterEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
