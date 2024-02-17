<script lang="ts">

import { onMounted, ref } from "vue";

import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import type { UserSecret } from "./UserSecret";
import UserSecretEditor from "./UserSecretEditor.vue";

export interface Props {
}
</script>

<script setup lang="ts">

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = UserSecret.TYPE;
const selection = ref<any>({});
onMounted(() => {
});

</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="Moment" data-field="creationDate">Creation Date</th>
            <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="net.bodz.bas.site.json.JsonMap" data-field="properties">Properties</th>
            <th data-type="string" data-format="label" data-field="user">The declaring user</th>
            <th data-type="string" data-field="password">Password data</th>
            <th data-type="string" data-field="question">Protection question</th>
            <th data-type="string" data-field="answer">Protection answer</th>
        </template>
        <template #preview>
            <UserSecretEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <UserSecretEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {}
}
</style>
