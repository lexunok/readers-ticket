<template>
    <div class="flex flex-col items-center">
        <div>{{ title }}</div>
        <div class="w-[75%] flex justify-center">
            <input v-if="redactive"
                    type="text" 
                    v-model="componentmodel"
                    @input="sendLocalComponent"
                    class="w-full text-xl rounded border border-slate-300 placeholder-slate-400 px-2
                            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
            <div v-else class="text-xl">{{ ElseTitle }}</div>
        </div>
    </div>
</template>
<script>
    export default {
        props: ['title', 'ElseTitle', 'getModel', 'model', 'redactive', 'property'],
        data(){
            return{
                componentmodel: this.ElseTitle
            }
        },
        methods: {
            sendLocalComponent () {
                this.getModel(this.property, this.componentmodel)
            }
        }
    }
</script>
<style>

</style>