<template>
  <div>
    <h1>数据列表</h1>
    <ul>
      <li v-for="item in items" :key="item.id">
        {{ item.name }} - {{ item.description }}
        <button @click="editItem(item)">编辑</button>
        <button @click="deleteItem(item.id)">删除</button>
      </li>
    </ul>
    <button @click="addItem">添加新数据</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          name:"nihao",
          data:"123-4"
        }
      ], // 存放从后端获取的数据
    };
  },
  created() {
    // 在组件创建时，从后端获取数据
    this.fetchData();
  },
  methods: {
    fetchData() {
      // 使用Axios或其他库发送HTTP GET请求来获取数据
      // 示例：axios.get('/api/items').then(response => this.items = response.data);
    },
    editItem(item) {
      // 编辑数据，导航到编辑页面或者弹出编辑表单
    },
    deleteItem(itemId) {
      // 发送HTTP DELETE请求来删除数据
      // 示例：axios.delete(`/api/items/${itemId}`).then(() => this.fetchData());
    },
    addItem() {
      // 导航到添加数据页面或者弹出添加数据表单
    },
  },
};
</script>
