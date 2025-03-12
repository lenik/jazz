<script lang="ts">
import { computed, onMounted, ref } from "vue";
import type { EntityPropertyMap } from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { CoImaged_TYPE } from "./CoImagedTypeInfo";
import CoImaged from "./CoImaged";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import AttachmentsEditor from "skel01-dba/src/ui/input/AttachmentsEditor.vue";

const model = defineModel<CoImaged<any>>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();

const className = computed(() => {
    let mod = model.value;
    let type = mod.getClass();
    console.log('mod.id', mod.id);
    console.log('getclass.simple', type.simpleName);
    return type.simpleName;
});

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="CoImaged_TYPE" ref="rootElement">
        <FieldRow :property="meta.properties" v-model="model.properties">
            <JsonEditor v-model="model.properties" />
        </FieldRow>
        <FieldRow :property="meta.images" v-model="model.images" v-if="model.files != null">
            <AttachmentsEditor :className="model.getClass().name" 
                :id="model.id" v-model="model.images" />
        </FieldRow>
        <FieldRow :property="meta.videos" v-model="model.videos" v-if="model.files != null">
            <AttachmentsEditor :className="model.getClass().name" 
                :id="model.id" v-model="model.videos" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss"></style>
