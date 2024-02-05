<template>
    <div class="flex flex-col items-center">
        <div class="2xl:text-base xl:text-sm lg:text-xs">{{ title }}</div>
        <div class="w-full flex justify-center">
            <input v-if="redactive"
                    type="text" 
                    v-model="componentmodel"
                    @input="sendLocalComponent"
                    class="w-full 2xl:text-xl xl:text-lg lg:text-base rounded border border-slate-300 placeholder-slate-400 px-2
                            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
            <div v-else class="2xl:text-xl xl:text-lg lg:text-base">{{ ElseTitle }}</div>
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