<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import SellPrice from "./SellPrice";

export const title = "Admin view of: Sell price";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import SellPriceEditor from "./SellPriceEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = SellPrice.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Artifact": Artifact_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="STRING" data-field="code">Code</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="BIG_DECIMAL" data-field="price">Price</th>
        </template>
        <template #preview>
            <SellPriceEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <SellPriceEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
