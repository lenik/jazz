<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { double, integer } from "@skeljs/core/src/lang/type";

import PostParameter from "./PostParameter";

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
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="string" data-format="label" data-field="post">Post</th>
            <th data-type="string" data-format="label" data-field="parameter">Parameter</th>
            <th data-type="integer" data-field="ival">Ival</th>
            <th data-type="double" data-field="fval">Fval</th>
            <th data-type="string" data-field="sval">Sval</th>
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
