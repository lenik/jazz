<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import AbstractDefinition from "./AbstractDefinition";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import SchemaDefChooseDialog from "./SchemaDefChooseDialog.vue";

const model = defineModel<AbstractDefinition<any>>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const schemaDefChooseDialog = ref<InstanceType<typeof SchemaDefChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="AbstractDefinition.TYPE" ref="rootElement">
        <FieldRow :property="meta.schema" v-model="model.schema">
            <RefEditor :dialog="schemaDefChooseDialog" v-model="model.schema" v-model:id="model.schemaId" />
        </FieldRow>
    </FieldGroup>
    <SchemaDefChooseDialog ref="schemaDefChooseDialog" />
</template>

<style scoped lang="scss"></style>
