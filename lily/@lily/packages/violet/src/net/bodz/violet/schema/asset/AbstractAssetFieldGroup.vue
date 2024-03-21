<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import AbstractAsset from "./AbstractAsset";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import ArtifactChooseDialog from "../art/ArtifactChooseDialog.vue";
import RegionChooseDialog from "../store/RegionChooseDialog.vue";

const model = defineModel<AbstractAsset>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
const regionChooseDialog = ref<InstanceType<typeof RegionChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="AbstractAsset.TYPE" ref="rootElement">
        <FieldRow :property="meta.quantity" v-model="model.quantity">
            <input type="number" v-model="model.quantity" />
        </FieldRow>
        <FieldRow :property="meta.serial" v-model="model.serial">
            <input type="number" v-model="model.serial" />
        </FieldRow>
        <FieldRow :property="meta.expire" v-model="model.expire">
            <DateTime v-model="model.expire" />
        </FieldRow>
        <FieldRow :property="meta.artifact" v-model="model.artifact">
            <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
        </FieldRow>
        <FieldRow :property="meta.region" v-model="model.region">
            <RefEditor :dialog="regionChooseDialog" v-model="model.region" v-model:id="model.regionId" />
        </FieldRow>
    </FieldGroup>
    <ArtifactChooseDialog ref="artifactChooseDialog" />
    <RegionChooseDialog ref="regionChooseDialog" />
</template>

<style scoped lang="scss">
</style>
