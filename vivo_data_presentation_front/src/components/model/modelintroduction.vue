<script setup>
import {ref} from "vue";
import {prop} from "dom7";
const props = defineProps(['propDate'])
// const Myarguments = ref(props.propDate)
const Myarguments = props.propDate
</script>

<template>
<div class = "top-name">
  模型细节
</div>
  <div class="name">
    参数
  </div>
  <li v-for="argument in Myarguments" class="list">
  <span class="argumentname">{{argument.argument}}:</span><span>{{argument.desriber}}</span>
  </li>
</template>

<style scoped>
.top-name{
  font-size: 30px;
  margin-top:30px;
  font-family: "Adobe 黑体 Std R";
  font-weight: bold;
}
.name{
  font-size: 20px;
  margin-top:30px;
  font-family: "Adobe 黑体 Std R";
  font-weight: bold;
}
.argumentname{
  font-size: 17px;
  margin-top:30px;
  font-family: "Adobe 黑体 Std R";
  font-weight: bold;
}
.list{
  margin: 20px 0px 20px 0px;
}

</style>