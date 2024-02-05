<template>
    <div :class="[classStyle]">
        <div :class="[titleStyle]">{{title}}</div> 
        <input v-if="textarea == null" 
                type="text" 
                v-model="componentmodel"
                @input="sendLocalComponent"
                :class="[inputStyle]"/>
        <textarea v-else type="text" 
                v-model="componentmodel"
                @input="sendLocalComponent"
                :class="[inputStyle]"/>
    </div>
</template>
<script>
    export default {
        props: ['title', 'classStyle', 'titleStyle', 'inputStyle', 'getModel', 'model', 'textarea', 'property'],
        data(){
            return{
                componentmodel: this.model
            }
        },
        methods: {
            sendLocalComponent () {
                this.getModel(this.property, this.componentmodel)
            }
        }
    }
</script>