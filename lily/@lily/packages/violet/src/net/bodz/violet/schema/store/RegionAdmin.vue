<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import { ArtifactCategory_TYPE } from "../art/ArtifactCategoryTypeInfo";
import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import Region from "./Region";
import { RegionCategory_TYPE } from "./RegionCategoryTypeInfo";
import { RegionLevel_TYPE } from "./RegionLevelTypeInfo";
import { RegionTag_TYPE } from "./RegionTagTypeInfo";
import { Region_TYPE } from "./RegionTypeInfo";

export const title = "Admin view of: Region";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import RegionEditor from "./RegionEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Region.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Region": Region_TYPE,
    "RegionLevel": RegionLevel_TYPE,
    "RegionCategory": RegionCategory_TYPE,
    "RegionTag": RegionTag_TYPE,
    "Artifact": Artifact_TYPE,
    "ArtifactCategory": ArtifactCategory_TYPE,
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
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="files">Files</th>
            <th data-type="STRING" data-field="path">Path</th>
            <th data-type="Region" data-format="label" data-field="proto">Proto</th>
            <th data-type="Region" data-format="label" data-field="parent">Parent</th>
            <th data-type="INT" data-field="depth">Depth</th>
            <th data-type="INT" data-field="height">Height</th>
            <th data-type="RegionLevel" data-format="label" data-field="level">Level</th>
            <th data-type="RegionCategory" data-format="label" data-field="category">Category</th>
            <th data-type="RegionTag" data-format="label" data-field="tag">Tag</th>
            <th data-type="Artifact" data-format="label" data-field="material">Material</th>
            <th data-type="ArtifactCategory" data-format="label" data-field="forCat">For Cat</th>
            <th data-type="Artifact" data-format="label" data-field="forArt">For Art</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        </template>
        <template #preview>
            <RegionEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <RegionEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
