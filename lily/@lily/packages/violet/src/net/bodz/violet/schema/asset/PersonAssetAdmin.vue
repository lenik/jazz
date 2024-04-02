<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { Region_TYPE } from "../store/RegionTypeInfo";
import PersonAsset from "./PersonAsset";

export const title = "Admin view of: Person asset";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PersonAssetEditor from "./PersonAssetEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PersonAsset.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Person": Person_TYPE,
    "Artifact": Artifact_TYPE,
    "Region": Region_TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "BIG_DECIMAL": BIG_DECIMAL,
    "ZonedDateTime": ZonedDateTime.TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="Person" data-format="label" data-field="owner">Owner</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="Region" data-format="label" data-field="region">Region</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="LONG" data-field="serial">Serial</th>
            <th data-type="ZonedDateTime" data-field="expire">Expire</th>
        </template>
        <template #preview>
            <PersonAssetEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PersonAssetEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
