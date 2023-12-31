<script setup>
// Vue3 Wangeditor 富文本编辑器
// 文档地址：https://www.wangeditor.com/v5/for-frame.html#安装-1
// 引入 css
import '@wangeditor/editor/dist/css/style.css'
import {onBeforeUnmount, ref, shallowRef, nextTick} from 'vue'
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'
import {ElMessage} from "element-plus";
import SkuSingle from "@/components/common/sku-single.vue";
import ImageCutterDialog from "@/components/common/image-cutter-dialog.vue";
import {ArrowRight, Delete, Share} from "@element-plus/icons-vue";

const form = ref({
    shopNo: '',
    shopName: '',
    shopType: [],
    unit: '0',
})

const shopType = [
    {
        value: 1,
        label: '笔记本',
    },
    {
        value: 2,
        label: '台式机',
    },
    {
        value: 3,
        label: '电脑组件',
        children: [
            {
                value: 31,
                label: '显示器',
            },
            {
                value: 32,
                label: '主板',
            },
        ],
    },
    {
        value: 4,
        label: '外设产品',
    },
    {
        value: 5,
        label: '电脑组件-2',
        children: [
            {
                value: 51,
                label: '显示器-2-1',
            },
            {
                value: 52,
                label: '主板-2-2',
            },
        ],
    },
    {
        value: 6,
        label: '电脑组件-3',
        children: [
            {
                value: 61,
                label: '显示器-3-1',
            },
            {
                value: 62,
                label: '主板-3-2',
            },
        ],
    },
]

// 初始化sku
const skus = ref([
    // 数据结构：
    {
        name: '',
        price: 0.01,
        delPrice: 0.02,
        count: 99,
    }
])

// ------- 富文本编辑器 --Start---------------------------------

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('<p></p>')
// 工具栏配置
const toolbarConfig = {
    toolbarKeys: [
        'headerSelect',
        '|',
        'bold', 'italic', "through", 'underline', 'color', 'bgColor', 'fontSize', "blockquote",
        '|',
        "insertTable",
        'uploadImage'
    ]
}
// 编辑区配置
const editorConfig = {
    placeholder: '请输入内容...',
    MENU_CONF: {
        uploadImage: {
            // 小于该值就插入 base64 格式（而不上传），默认为 0
            base64LimitSize: 5 * 1024, // 5kb
            async customUpload(file, insertFn) {
                // file 即选中的文件
                imgUpload(file, (src) => {
                    // 自己实现上传，并得到图片 url alt href
                    // 最后插入图片
                    insertFn(src, '', 'javascript:(0);')
                })
            }
        }
    },
    hoverbarKeys: {
        'image': {
            // 清空 image 元素的 hoverbar
            menuKeys: [],
        }
    }
}


/**
 * 图片上传
 * @param file 选择的文件
 * @param cb 回调
 */
const imgUpload = (file, cb) => {
    // 文件大小限制，1M
    let maxFileSize = 1 * 1024 * 1024
    console.log(file)
    if (file.size > maxFileSize) {
        ElMessage.error("单张图片不能大于1M！");
        return;
    }
    cb('https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg');
}


/**
 * 记录 editor 实例，重要！
 * @param editor
 */
const handleCreated = (editor) => {
    editorRef.value = editor
    console.log(editor.getAllMenuKeys())
}

// 组件销毁时，也及时销毁编辑器，重要！
onBeforeUnmount(() => {
    const editor = editorRef.value
    if (editor == null) return
    editor.destroy()
})
// ------- 富文本编辑器 --End---------------------------------

// 商品标签
const dynamicTags = ref([])
const inputValue = ref('')
const inputVisible = ref(false)

const inputRef = ref(null); // 创建一个ref来引用el-input

/**
 * 删除Tag
 * @param tag
 */
const handleCloseTag = tag => {
    dynamicTags.value.splice(dynamicTags.value.indexOf(tag), 1)
}

/**
 * 添加Tag
 */
const handleInputTagConfirm = () => {
    if (inputValue.value) {
        dynamicTags.value.push(inputValue.value)
    }
    inputVisible.value = false
    inputValue.value = ''
}

/**
 * 显示添加Tag
 */
const showInput = () => {
    inputVisible.value = true
    nextTick(() => {
        if (inputRef.value) {
            inputRef.value.focus()
        }
    })
}

// ----------- 添加商品图片 --Start-----------------------
const images = ref([])
const isShowChooseShopImgCutImgDialog = ref({show: false})

let imageId = 0;
const chooseShopImgCutImgCallback = (e) => {
    imageId++
    images.value.push({
        id: imageId,
        src: e.dataURL
    })
}

const openChooseShopImgCutImgDialog = () => {
    isShowChooseShopImgCutImgDialog.value.show = true
}

// ----------- 添加商品图片 --End-----------------------

const units = ref([
    {
        label: '天',
        value: '0',
        disabled: false
    },
    {
        label: '月',
        value: '1',
        disabled: true
    },
    {
        label: '小时',
        value: '2',
        disabled: true
    },
])

// ----------- 商品预览 --Start-----------------------
const preViewWidth = 360;
const carouselHeight = preViewWidth * 0.7;

// ----------- 商品预览 --End-----------------------


</script>

<template>
    <div class="page">
        <div class="page_left">
            <el-card shadow="never" class="vel_card_override">
                <div class="add_goods_panel">
                    <el-scrollbar>
                        <el-form :model="form" label-width="120px">
                            <el-form-item label="商品编号" style="padding-top: 20px">
                                <el-input v-model="form.shopNo" style="width: 150px"/>
                            </el-form-item>
                            <el-form-item label="商品名称">
                                <el-input v-model="form.shopName"/>
                            </el-form-item>
                            <el-form-item label="促销信息">
                                <el-input
                                    v-model="form.desc"
                                    :autosize="{ minRows: 2, maxRows: 4 }"
                                    type="textarea"
                                    placeholder="请输入简短的描述信息，100个字符以内！"
                                />
                            </el-form-item>
                            <el-form-item label="商品图" class="shop_images">
                                <div class="shop_img_list" v-for="img in images" :key="img.id">
                                    <el-image class="shop_img" :src="img.src" fit="fill"/>
                                    <span class="mask">
                                        <el-icon>
                                            <Delete/>
                                        </el-icon>
                                    </span>
                                </div>
                                <div v-if="images.length < 7" class="el-upload--picture-card"
                                     @click="openChooseShopImgCutImgDialog">
                                    <el-icon>
                                        <Plus/>
                                    </el-icon>
                                </div>
                            </el-form-item>
                            <el-form-item class="shop_tags" label="商品标签">
                                <el-tag
                                    v-for="tag in dynamicTags"
                                    :key="tag"
                                    size="large"
                                    closable
                                    :disable-transitions="false"
                                    @close="handleCloseTag(tag)">
                                    {{ tag }}
                                </el-tag>
                                <el-input
                                    v-if="inputVisible"
                                    v-model="inputValue"
                                    size="default"
                                    ref="inputRef"
                                    @keyup.enter="handleInputTagConfirm"
                                    @blur="handleInputTagConfirm"
                                    style="width: 100px"
                                />
                                <el-button v-else size="default" @click="showInput">
                                    + 添加标签
                                </el-button>
                            </el-form-item>
                            <el-form-item label="商品分类">
                                <el-cascader
                                    :props="{expandTrigger:'hover'}"
                                    :options="shopType"
                                    size="default"
                                    v-model="form.shopType" clearable placeholder="选择分类"/>
                            </el-form-item>
                            <el-form-item label="规格" class="sku_panel">
                                <sku-single :skus="skus"></sku-single>
                            </el-form-item>
                            <el-form-item label="价格单位">
                                <el-select v-model="form.unit" size="default" placeholder="Select">
                                    <template v-for="option in units" :key="option.value">
                                        <el-option :label="option.label" :value="option.value"
                                                   :disabled="option.disabled"/>
                                    </template>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="商品描述" class="shop_editor">
                                <Toolbar
                                    style="border-bottom: 1px solid #e0e0e0"
                                    :editor="editorRef"
                                    :defaultConfig="toolbarConfig"
                                    mode="simple"
                                />
                                <Editor
                                    style="height:400px;overflow-y: hidden;"
                                    v-model="valueHtml"
                                    :defaultConfig="editorConfig"
                                    mode="simple"
                                    @onCreated="handleCreated"
                                />
                            </el-form-item>
                        </el-form>
                    </el-scrollbar>
                </div>
            </el-card>
        </div>
        <div class="page_right">
            <div class="preview">
                <div class="preview_con">
                    <el-scrollbar>
                        <div class="shop_carousel_list">
                            <el-carousel :height="carouselHeight + 'px'">
                                <el-carousel-item v-for="img in images" :key="img.id">
                                    <el-image :src="img.src" fit="fill" style="width: 100%"/>
                                </el-carousel-item>
                            </el-carousel>
                        </div>
                        <div class="shop_info_panel">
                            <div class="tit_panel">
                                <div class="tit_item price">
                                    <span class="price_real">
                                        <el-text>￥</el-text>
                                        <el-text style="font-size: 22px" tag="b">{{ skus[0].price }}</el-text>
                                        <el-text>/{{ units[0].label }}</el-text>
                                    </span>
                                    <el-text type="info" tag="del" size="small">￥{{ skus[0].delPrice }}/{{ units[0].label }}</el-text>
                                </div>
                                <div class="tit_item tit">
                                    <el-text class="txt" tag="b" :style="{width:preViewWidth-20-40 + 'px'}">{{ form.shopName }}</el-text>
                                    <div class="share">
                                        <el-icon>
                                            <Share/>
                                        </el-icon>
                                        <el-text size="small">分享</el-text>
                                    </div>
                                </div>
                                <div v-if="form.desc !== ''" class="tit_item desc" :style="{width:preViewWidth-20 + 'px'}">
                                    <el-text type="info" size="small">{{ form.desc }}</el-text>
                                </div>
                                <div v-if="dynamicTags.length > 0" class="tit_item tags">
                                    <el-tag v-for="tag in dynamicTags" :key="tag" type="danger" size="small" effect="plain">{{tag}}</el-tag>
                                </div>
                            </div>
                            <div class="sku_panel">
                                <div style="padding-bottom: 10px">
                                    <el-text tag="b">规格</el-text>
                                </div>
                                <div class="sku_info">
                                    <el-text class="sku_name" type="info" :style="{width:preViewWidth-20-40*2 + 'px'}" size="small">
                                        {{skus[0].name}}
                                    </el-text>
                                    <el-text class="sku_num" type="info">x1</el-text>
                                    <div class="choose_more_arrow">
                                        <el-icon><ArrowRight/></el-icon>
                                    </div>
                                </div>
                            </div>
                            <div class="choose_time_panel">
                                <div style="padding-bottom: 10px">
                                    <el-text tag="b">租赁周期</el-text>
                                </div>
                                <div class="time_panel">
                                    <el-steps direction="vertical" :active="1" :space="40">
                                        <el-step status="wait">
                                            <template #description>
                                                开始时间 : 2023-11-26
                                            </template>
                                        </el-step>
                                        <el-step status="wait">
                                            <template #description>
                                                结束时间 : 2023-11-26
                                            </template>
                                        </el-step>
                                    </el-steps>
                                    <div class="choose_more_arrow">
                                        <el-icon><ArrowRight/></el-icon>
                                    </div>
                                </div>
                            </div>
                            <div class="detail_panel">
                                <el-tabs :model="0">
                                    <el-tab-pane label="商品详情">
                                        <div v-html="valueHtml" :style="{width:preViewWidth-20 + 'px'}"></div>
                                    </el-tab-pane>
                                    <el-tab-pane label="租赁流程">Config</el-tab-pane>
                                </el-tabs>
                            </div>
                        </div>
                    </el-scrollbar>
                </div>
            </div>
            <div class="publish">
                <el-button type="primary" size="large" style="width: 100%">立即发布
                </el-button>
            </div>
        </div>
        <image-cutter-dialog :dialog="isShowChooseShopImgCutImgDialog" @onConfirm="chooseShopImgCutImgCallback"
                             :cut-width="300"
                             :cut-height="210"></image-cutter-dialog>
    </div>
</template>

<style scoped>
.page {
    height: calc(100vh - 90px);
    display: flex;
}

.page_left {
    width: 100%;
    padding: 20px 0px 20px 20px;
}

:deep(.vel_card_override) .el-card__body {
    padding: 0;
}

.add_goods_panel {
    height: calc(100vh - 90px - 20px - 15px - 2px);
    overflow: hidden;
}

.add_goods_panel .el-form-item {
    padding-right: 20px;
}

.add_goods_panel .shop_images, .add_goods_panel .sku_panel {
    background-color: #f8faff;
}

.shop_images {
    padding: 10px 0;
}

.shop_img_list {
    width: 120px;
    height: 84px;
    border-radius: 5px;
    border: 1px solid #efefef;
    position: relative;
}

.shop_img_list .mask {
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    cursor: default;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    opacity: 0;
    font-size: 20px;
    background-color: var(--el-overlay-color-lighter);
    transition: opacity var(--el-transition-duration);
}

.shop_img_list .mask:hover {
    cursor: pointer;
    opacity: 1;
    border-radius: 5px;
}

.shop_img {
    border-radius: 5px;
    width: 120px;
    height: 84px;
}

.shop_images .el-upload--picture-card {
    --el-upload-picture-card-size: 84px;
}

:deep(.shop_images) .el-form-item__content {
    gap: 10px;
}

.shop_tags .el-tag,
.shop_tags .el-button,
.shop_tags .el-input{
    margin-right: 5px;
    margin-bottom: 3px;
}

:deep(.shop_editor) .el-form-item__content {
    flex-direction: column;
    align-items: normal;
    border: 1px solid #e9eaef;
    line-height: normal;
}

.page_right {
    min-width: 400px;
    display: flex;
    flex-direction: column;
}

.page_right .preview {
    padding: 20px 20px 0 20px;
}

.page_right .preview_con {
    background-color: #ffffff;
    height: calc(100vh - 90px - 20px - 70px);
    overflow: hidden;
    border-radius: 4px 4px 0 0;
}

.el-carousel {
    background-color: #f0f2f5;
}

:deep(.shop_carousel_list) .el-carousel__indicators--horizontal {
    width: 360px;
    display: flex;
    justify-content: center;
    transform: none;
    left: 0;
}

.shop_info_panel {
    background-color: #f0f2f5;
}

.tit_panel,
.sku_panel,
.choose_time_panel,
.detail_panel{
    background-color: #ffffff;
    padding: 10px;
    margin-bottom: 10px;
}

.tit_item:not(:last-child) {
    padding-bottom: 10px;
}

.tit_item.tags .el-tag{
    margin-right: 5px;
    margin-bottom: 3px;
}

.price .price_real{
    padding-right: 7px;
}

.price .el-text{
    color: red;
}

.tit_panel .tit{
    display: flex;
    align-items: center;
}

.tit .txt{
    flex-grow: 1;
    overflow: hidden; /* 超出部分隐藏 */
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; /* 设置行数 */
}

.tit .share{
    width: 50px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.sku_panel .sku_info{
    display: flex;
}

.sku_info .sku_name{
    flex-grow: 1;
    overflow: hidden; /* 超出部分隐藏 */
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; /* 设置行数 */
}

.sku_info .sku_num{
    width: 40px;
    text-align: center;
}

.sku_info .choose_more_arrow{
    width: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
}

:deep(.choose_time_panel) .el-step:last-child{
    flex-basis: 0 !important;
}

.choose_time_panel .time_panel{
    display: flex;
}

.choose_time_panel .el-steps{
    flex-grow: 1;
}

.choose_time_panel .choose_more_arrow{
    width: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
}

:deep(.detail_panel) .el-tab-pane img{
    width: 100% !important;
    height: auto !important;
    vertical-align: bottom;
}

.page_right .publish {
    height: 70px;
    padding: 0 20px;
    display: flex;
    align-items: center;
}


</style>