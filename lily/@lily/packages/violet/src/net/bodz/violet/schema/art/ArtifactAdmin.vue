<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, SHORT, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Uom from "@lily/basic/src/net/bodz/lily/schema/util/Uom";

import Artifact from "./Artifact";
import ArtifactCategory from "./ArtifactCategory";
import ArtifactPhase from "./ArtifactPhase";

export const title = "Admin view of: Artifact";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import ArtifactEditor from "./ArtifactEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Artifact.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Artifact": Artifact.TYPE,
    "ArtifactCategory": ArtifactCategory.TYPE,
    "ArtifactPhase": ArtifactPhase.TYPE,
    "Uom": Uom.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "SHORT": SHORT,
    "BIG_DECIMAL": BIG_DECIMAL,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="STRING" data-field="skuCode">Sku Code</th>
            <th data-type="STRING" data-field="barCode">Bar Code</th>
            <th data-type="STRING" data-field="rfidCode">Rfid Code</th>
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
            <th data-type="STRING" data-field="modelName">Model Name</th>
            <th data-type="Artifact" data-format="label" data-field="proto">Proto</th>
            <th data-type="ArtifactCategory" data-format="label" data-field="category">Category</th>
            <th data-type="ArtifactPhase" data-format="label" data-field="phase">Phase</th>
            <th data-type="Uom" data-format="label" data-field="uom">Uom</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="SHORT" data-field="finish">Finish</th>
            <th data-type="BIG_DECIMAL" data-field="price">Price</th>
        </template>
        <template #preview>
            <ArtifactEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ArtifactEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
