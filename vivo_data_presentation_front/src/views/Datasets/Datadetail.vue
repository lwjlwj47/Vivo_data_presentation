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
      <el-tab-pane label="代码" name="second">这里是模型代码<br>展示模型的代码</el-tab-pane>
      <el-tab-pane label="在线测试" name="third">这里是模型在线测试<br>在此模块中，我们可以选择我们想要的数据集输入至后端，获取后端返回结果</el-tab-pane>
      <el-tab-pane label="在线持续训练" name="fourth">这里是模型持续训练<br>在次模块中，我们可以输入最新的训练集数据对模型进行进一步训练</el-tab-pane>
      <el-tab-pane label="讨论" name="fifth">这里是模型相关讨论</el-tab-pane>
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