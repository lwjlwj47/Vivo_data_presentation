<script setup>
import {useRoute,useRouter} from "vue-router";
const router = useRouter()
const route  = useRoute()
import {defineComponent} from 'vue'
import { ref } from 'vue'
import Modelintroduction from "@/components/model/modelintroduction.vue";
const activeName = ref('first')
const model= route.query
console.log(JSON.parse(model.arguments))
</script>

<template>
  <el-card class="vel_card_override">
  <div class="top">
    <div class="top-describe">
      <el-text class="top-model">{{model.name}}</el-text>
      <el-text>{{model.description}}</el-text>
    </div>
  </div>
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
    <el-tab-pane label="模型介绍" name="first"><modelintroduction :propDate="JSON.parse(model.arguments)"></modelintroduction></el-tab-pane>
    <el-tab-pane label="代码" name="second">Config</el-tab-pane>
  </el-tabs>
</el-card>
</template>

<style scoped>
.vel_card_override{
  margin: 20px 20px 0px 20px;
  height: calc(100vh - 90px - 20px - 20px - 2px);
}
.top{
  display: flex;
  justify-content: space-between;
  padding: 30px 150px 30px 150px ;
  background-color: #ffffff;
}
.top-describe{
  display: flex;
  justify-content: center;
  flex-direction:column;
}
.top-model{
  font-size: 40px;
  font-family: 黑体;
  font-weight: bold;
  color: #1b1f23;
  margin-bottom: 10px;
}
.demo-tabs{
  margin:30px 150px 30px 150px ;
}

</style>