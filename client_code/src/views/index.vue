<template>
  <div class="home">
    <div class="index_top">
      <div class="index_top_title">
        <span>基于springboot + vue的咖啡厅座位预约平台</span>
      </div>
      <div class="menu-wrapper">
        <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
          <el-menu
            :default-openeds="[]"
            :unique-opened="true"
            :default-active="menuIndex"
            class="menu_view"
            mode="horizontal"
            :ellipsis="false"
            @select="menuChange"
            :key="menuIndex"
          >
            <el-menu-item
              class="first-item"
              index="/index/home"
              @click="menuHandler('/')"
            >
              <i class="iconfont icon-zhuye2"></i>
              <template #title>
                <span>首页</span>
              </template>
            </el-menu-item>
            <template v-for="(menu, index) in menuList" :key="menu.menu">
              <el-sub-menu
                v-if="menu.child.length > 1"
                :index="menu.name"
                class="first-item"
                :teleported="true"
              >
                <template #title>
                  <i class="iconfont" :class="menu.icon"></i>
                  <span>{{ menu.name }}</span>
                </template>
                <el-menu-item
                  class="second-item"
                  v-for="(child, index1) in menu.child"
                  :key="index1"
                  :index="child.url"
                  @click="menuHandler(child.url)"
                  >{{ child.name }}
                </el-menu-item>
              </el-sub-menu>
              <el-menu-item
                v-else
                :index="menu.child[0].url"
                class="first-item"
                @click="menuHandler(menu.child[0].url)"
              >
                <i class="iconfont" :class="menu.icon"></i>
                <template #title>
                  <span>{{ menu.child[0].name }}</span>
                </template>
              </el-menu-item>
            </template>
          </el-menu>
        </el-scrollbar>
      </div>
      <div class="index_top_right">
        <el-button
          v-if="!Token"
          class="login"
          type="success"
          icon="User"
          circle
          @click="loginClick"
        >
          登录
        </el-button>
        <div class="user" v-if="Token">
          <el-dropdown class="avatar-container" trigger="hover">
            <div class="avatar-wrapper">
              <img
                class="user-avatar"
                :src="store.getters['user/avatar']"
                style="width: 50px"
              />
              <div class="nickname">{{ store.getters['user/username'] }}</div>
              <el-icon class="el-icon-arrow-down">
                <arrow-down />
              </el-icon>
            </div>
            <template #dropdown>
              <el-dropdown-menu class="user-dropDown" slot="dropdown">
                <el-dropdown-item @click="menuHandler('center')" class="center">
                  <span>个人中心</span>
                </el-dropdown-item>
                <el-dropdown-item @click="loginOut" class="loginOut">
                  <span>退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </div>
    <el-scrollbar class="contain_view">
      <div class="rotation_view">
        <mySwiper
          :type="3"
          :data="rotationList"
          :autoHeight="false"
          :autoplay="true"
          :loop="false"
          :navigation="false"
          :pagination="false"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="0"
          :direction="horizontal"
        >
          <template #default="scope">
            <img
              :style="{}"
              :src="scope.row.value ? $config.url + scope.row.value : ''"
              @click="carouselClick(scope.row.url)"
            />
          </template>
        </mySwiper>
      </div>
      <router-view :key="routerKey" />
      <el-backtop :right="100" :bottom="100" />
      <div class="bottom_view">
        <img
          class="bottom_img"
          src="http://localhost:8080/coffeeShopSeatReserva/file/0d5ae5e141c14b06a52a7c4c2f8ea4cf.jpg"
          alt=""
        />
        <div class="bottom_company"></div>
        <div class="bottom_record"></div>
        <div class="bottom_desc"></div>
      </div>
    </el-scrollbar>
  </div>
</template>
<script setup>
import menu from '@/utils/menu'
import axios from 'axios'
import moment from 'moment'
import {
  ref,
  onBeforeUnmount,
  getCurrentInstance,
  nextTick,
  computed,
  watch,
  provide,
} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
const store = useStore()
const router = useRouter()
const route = useRoute()
const context = getCurrentInstance()?.appContext.config.globalProperties
const Token = ref('')
const dateTimer = ref(null)
const nowDate = ref(new Date())
if (localStorage.getItem('frontToken') && !store.getters['user/session'].id) {
  store.dispatch('user/getSession')
}
onBeforeUnmount(() => {
  clearInterval(dateTimer.value)
})
// 获取默认菜单index
const setMenuIndex = (path) => {
  if (path) {
    menuIndex.value = path.replace('Detail', 'List')
  } else {
    menuIndex.value = ''
  }
}
// 默认菜单index
const menuIndex = ref('')
const routerKey = ref(Math.random())
watch(
  () => router.currentRoute.value,
  (value, oldValue) => {
    Token.value = context?.$toolUtil.storageGet('frontToken')
    if (value.path == oldValue?.path) {
      //改变路由query时重新加载router-view
      routerKey.value = Math.random()
    }
    if (value.query.centerType) {
      setMenuIndex()
    } else {
      setMenuIndex(value.path)
    }
  },
  {
    immediate: true,
  }
)
const init = () => {
  // 获取菜单
  getMenu()
  // 赋值token
  Token.value = context?.$toolUtil.storageGet('frontToken')
  // 时间
  dateTimer.value = setInterval(() => {
    nowDate.value = new Date()
  }, 1000)
  // 轮播图
  getRotationList()
  if (Token.value) {
    getSession()
  }
}
// 切换菜单保存index
const menuChange = (e) => {
  if (e == 'chat') return
}
// 轮播图
const rotationList = ref([])
const baseUrl = context?.$config.url
const getRotationList = () => {
  context
    ?.$http({
      url: 'config/list',
      method: 'get',
      params: {
        page: 1,
        limit: 3,
      },
    })
    .then((res) => {
      rotationList.value = res.data.data.list
    })
}

// 轮播图跳转
const carouselClick = (url) => {
  if (url) {
    if (url.startsWith('http')) {
      window.open(url)
    } else {
      context.$router.push(url)
    }
  }
}
const menuList = ref([])
const role = ref('')
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }
  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    })
  menuList.value = context?.$config.menuList
}
const loginClick = () => {
  context?.$toolUtil.storageSet('toPath', window.history.state.current)
  router.push('/login')
}
const loginOut = () => {
  context?.$toolUtil.message('退出成功', 'success')
  context?.$toolUtil.storageClear()
  router.replace('/index/home')
  Token.value = ''
}
//菜单跳转
const menuHandler = (name) => {
  if (name == 'center') {
    name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
    menuChange('center')
  }
  router.push(name)
}
// 获取用户信息
const getSession = () => {
  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      context?.$toolUtil.storageSet('userid', res.data.data.id)
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu') {
        context?.$toolUtil.storageSet('frontName', res.data.data.yonghuming)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu') {
        context?.$toolUtil.storageSet(
          'headportrait',
          res.data.data.touxiang || ''
        )
      }
    })
}
init()
</script>
<style lang="scss" scoped>
/* 头部 */
.index_top {
  .index_top_title {
  }
  .index_top_right {
    .weather_time_view {
      .weather {
        .city {
        }
        .wea {
        }
      }
      .time {
        .date {
        }
        .timer {
        }
      }
    }
  }
  // 登录按钮
  :deep(.el-button--success) {
  }
  :deep(.el-button--success:hover) {
  }
  // 登出按钮
  :deep(.el-button--danger) {
  }
  :deep(.el-button--danger:hover) {
  }
}
// 轮播盒子
.rotation_view {
}
// 底部
.bottom_view {
  // 图片
  .bottom_img {
    display: none;
  }
  // 公司
  .bottom_company {
  }
  // 备案号
  .bottom_record {
  }
  // 联系方式
  .bottom_desc {
  }
}

.contain_view {
  padding: 210px 0 0;
}
.el-aside {
  transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -moz-transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -o-transition: width 0.15s;
}
.el-sub-menu__hide-arrow {
  display: block !important;
}
.menu_scrollbar {
}
// 总盒子
.menu_view {
  // 一级菜单
  :deep(.first-item) {
    // 图标
    .iconfont {
    }

    // 标题
    .el-sub-menu__title,
    span {
    }

    //箭头
    .el-sub-menu__icon-arrow {
    }
  }
  // 选中
  :deep(.is-active) {
  }

  // 悬浮
  :deep(.first-item:hover) {
  }
}
</style>
<style lang="scss">
// 二级
.menu_popper {
  border: none !important;
  background: none !important;

  // 二级总盒子
  .el-menu--popup {
  }

  // 二级菜单
  .second-item {
  }

  // 选中
  .is-active {
  }

  // 悬浮
  .second-item:hover {
  }
}
</style>
<style lang="scss">
.index_top {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  background: #fff;
  justify-content: space-between;
  box-sizing: border-box;
  z-index: 1002;
  position: fixed;
  border-bottom: 0px solid #edeef0;

  overflow: hidden;
  color: rgba(66, 66, 66, 1);
}

.index_top .index_top_title {
  font-size: 24px;
  font-weight: bold;
  order: 0;
  line-height: 80px;
  order: 2;
  padding-left: calc(50% - 700px);
  width: 100%;
  display: flex;
  align-items: center;
}

.index_top .index_top_right {
  display: flex;
  align-items: center;
  column-gap: 20px;
  width: 100%;
  background: rgba(243, 245, 247, 1);
  padding: 18px calc(50% - 700px);
  justify-content: flex-end;
}

.index_top .notice-btn {
  background: var(--theme);
  padding: 0px 6px;
  font-size: 16px;
  border-radius: 0;
  color: #fff;
  order: 1;
  border: none;
  height: 30px;
  border-radius: 4px;
}
.index_top .notice-btn:hover {
  color: var(--theme);
}

.index_top .notice-btn .iconfont {
  font-size: 16px;
}

.index_top .index_top_right .chat {
  order: 3;
}
.index_top .index_top_right .chat:hover {
  color: var(--theme);
}
.index_top .index_top_right .chat .iconfont {
  font-size: 16px;
}
.index_top .index_top_right .chat span {
  font-size: 16px;
}

.index_top .index_top_right .cart {
  font-size: 16px;

  order: 2;
}
.index_top .index_top_right .cart:hover {
  color: var(--theme);
}
.index_top .index_top_right .cart .iconfont {
  font-size: 16px;
  margin-right: 5px;
}

.index_top .user .avatar-wrapper {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: inherit;
}

.index_top .user .avatar-wrapper .user-avatar {
  width: 36px !important;
  height: 36px;
  border-radius: 100%;
  margin-right: 5px;
}

.index_top .user .avatar-wrapper .nickname {
  font-size: 16px;
  margin-right: 5px;
}

.index_top .user .avatar-wrapper .el-icon-arrow-down {
}

.index_top .user-dropDown {
  padding: 10px 0;
  margin: 5px 0;
  background: #fff;
  border: 1px solid #e6ebf5;
  border-radius: 4px;
  -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.index_top .user-dropDown li {
  padding: 0 20px !important;
  line-height: 36px !important;
  font-size: 14px !important;
  color: #606266 !important;
}
.index_top .user-dropDown li:hover {
  color: #fff !important;
  background: var(--theme) !important;
}

.index_top .weather_time_view {
  order: 0;
  display: flex;
  align-items: center;
  line-height: 20px;
  font-size: 16px;
  column-gap: 10px;
}

.index_top .login {
  order: 5;
}

.index_top .user {
  order: 5;
}

.index_top .weather {
  display: flex;
  align-items: center;
}

.index_top .time {
  display: flex;
  align-items: center;
  column-gap: 10px;
}

.index_top .login {
  background: var(--theme);
  width: 80px;
  border: none;
  height: 30px;
  border-radius: 4px;
  padding: 0 4px;
}

.index_top .toBack button {
  background: var(--theme);
  color: #fff;
  border-radius: 0;
  border: 0;
  height: 30px;
  padding: 0 6px;
  border-radius: 4px;
}

.index_top .toBack {
  order: 4;
}
.index_top button.el-button:hover,
.index_top button.el-button:focus {
  background: #fff;
  border-color: var(--theme);
  color: var(--theme);
}

.index_top .menu-wrapper {
  order: 3;
}

.index_top .el-button.loginOut {
  background: var(--theme);
  border: none;
  border-radius: 4px;
  height: 30px;
  padding: 0px 10px;
  order: 5;
  margin: 0;
}
.bottom_view {
  width: 100%;
  background: rgba(0, 0, 0, 1);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 40px 0px;
  border-width: 0px 0px 0px;
  border-style: solid;
  border-color: rgb(254, 176, 67);
  font-size: 16px;
  color: rgb(51, 51, 51);
  line-height: 1.8;
}

.menu-wrapper {
  text-align: center;
  width: 100%;
}

.menu-wrapper .menu_view {
  display: flex;
  border-bottom: none;
  align-items: center;
  margin: 0 calc(50% - 694px) 0 calc(50% - 707px);
  background: rgba(243, 245, 247, 1);
  border-radius: 30px;
}

.menu-wrapper .first-item {
  color: rgba(102, 106, 111, 1) !important;
}

.menu-wrapper .el-sub-menu__title {
  color: inherit !important;
  border-bottom: none !important;
  height: 50px !important;
  line-height: 50px !important;
}

.menu-wrapper .el-sub-menu__title > * {
  color: inherit !important;
}

.menu-wrapper .first-item > i {
  color: inherit;
}

.menu-wrapper .first-item.is-active {
  color: #fff !important;
  border-bottom: none !important;
  background: var(--theme) !important;
  border-radius: 30px;
}
.menu-wrapper .first-item:hover {
  background: var(--theme) !important;
  color: #fff !important;
  line-height: 50px;
  height: 50px;
}
.menu-wrapper .el-menu-item.second-item.is-active {
  color: var(--theme) !important;
  background: #fff;
}

.menu-wrapper .second-item:hover {
  background: #fff !important;
  color: #333 !important;
}

.menu_view > .first-item {
  line-height: 50px;
  height: 50px;
  border-radius: 30px;
  flex: 1;
  font-size: 18px;
}

li.el-menu-item.second-item:hover,
li.el-menu-item.second-item.is-activ {
  color: var(--theme) !important;
}

.menu-wrapper .first-item .el-sub-menu__title {
  background: inherit !important;
  border-radius: 30px;
  font-size: 18px;
}

.menu-wrapper .first-item.is-active > .el-sub-menu__title {
  background: var(--theme) !important;
}

.rotation_view {
  width: 100%;
  margin: 0px auto;
  padding: 0 calc(50% - 700px);
}

.rotation_view .swiper-container {
  width: 100%;
  height: 100%;
  border: 0px solid #fff;
  border-radius: 10px;
}
.rotation_view .swiper .swiper-wrapper {
  transition-duration: 0ms;
  transform: translate3d(0px, 0px, 0px);
  height: 500px;
}
.rotation_view .swiper .swiper-wrapper .swiper-slide {
}
.rotation_view .swiper .swiper-wrapper .swiper-slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.rotation_view .swiper .swiper-button-prev {
  margin-left: calc((100% - 1180px) / 2);
  color: var(--theme);
}

.rotation_view .swiper .swiper-button-next {
  margin-right: calc((100% - 1180px) / 2);
  color: var(--theme);
}

.bread_view {
  width: 100%;
  background: none;
  color: rgb(51, 51, 51);
  text-align: center;
  font-size: 20px;
  font-weight: 600;
  border: 0px solid #eee;
  padding: 10px 0px 10px 0px;
  background: #fff;
}

.bread_view .el-breadcrumb {
  font-size: 16px;
  line-height: 1;
}

.bread_view .el-breadcrumb .el-breadcrumb__separator {
  margin: 0px 9px;
  font-weight: 500;
  color: #000;
}

.bread_view .el-breadcrumb .first_breadcrumb span a {
  color: #000;
  display: inline-block;
}

.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner {
  color: var(--theme);
  display: inline-block;
}

.bread_view {
  width: 1400px;
  margin: 0 calc(50vw - 700px);
  position: relative;
  left: calc(-50vw + 700px);
  height: 50px;
  align-items: center;
  display: flex;

  background: rgba(243, 245, 247, 1);
  padding: 0 50px;
  margin-top: 20px;
}

.back_view {
  padding: 10px 0;
}
.back_btn {
  background: var(--theme);
  border: none;
  color: #fff;
}
.back_btn:hover {
  background: var(--theme);
  color: #fff;
}

.bread_view .el-breadcrumb span {
  margin-right: 50px;
}
</style>
