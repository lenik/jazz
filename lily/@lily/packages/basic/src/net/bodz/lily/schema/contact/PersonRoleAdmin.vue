<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";

import PersonRole from "./PersonRole";

export const title = "Admin view of: Person role";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PersonRoleEditor from "./PersonRoleEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PersonRole.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="org">Org</th>
            <th data-type="string" data-format="label" data-field="orgUnit">Org Unit</th>
            <th data-type="string" data-format="label" data-field="person">Person</th>
            <th data-type="string" data-field="role">Role</th>
            <th data-type="string" data-field="description">Description</th>
        </template>
        <template #preview>
            <PersonRoleEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PersonRoleEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
