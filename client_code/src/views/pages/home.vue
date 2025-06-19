<template>
  <div>
    <div class="home_box">
      <!-- 公告 -->
      <div class="newsList_view">
        <div class="newtitle">
          <div class="in">公告</div>
          <div class="nmore" @click="moreClick('news')" style="cursor: pointer">
            查看更多
          </div>
        </div>

        <div class="list">
          <template v-for="(item, index) in newsList">
            <div class="item" v-if="index != 4" @click="newsDetailClick(item)">
              <div class="n1">新闻资讯</div>
              <div class="title">{{ item.title }}</div>
              <div class="time">
                <span class="time_in">{{
                  moment(item.addtime).format('YYYY-MM-DD')
                }}</span>
              </div>
            </div>
            <div class="item1" v-else @click="newsDetailClick(item)">
              <img :src="item.imgUrl" />
              <div class="infobox">
                <div class="title">{{ item.title }}</div>
                <div class="intro">{{ item.introduction }}</div>
                <div class="time">
                  <span class="time_in">{{
                    moment(item.addtime).format('YYYY-MM-DD')
                  }}</span>
                </div>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
    <formModel ref="newsFormModelRef"></formModel>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue'
import moment from 'moment'
import { useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
//公告弹窗
import formModel from './news/formModel'
const newsFormModelRef = ref(null)
//公告
const newsList = ref([])
const getNewsList = () => {
  context
    ?.$http({
      url: 'news/list',
      method: 'get',
      params: {
        page: 1,
        limit: 9,
        sort: 'id',
        order: 'desc',
      },
    })
    .then((res) => {
      newsList.value = res.data.data.list
      newsList.value.forEach((item) => {
        if (!isHttp(item.picture)) {
          item.imgUrl = context.$config.url + item.picture.split(',')[0]
        }
      })
    })
}
const newsDetailClick = (item) => {
  if (item && item.id) {
    newsFormModelRef.value.init(item.id)
  }
}
//判断图片链接是否带http
const isHttp = (str) => {
  return str && str.substr(0, 4) == 'http'
}
//跳转详情
const detailClick = (table, id) => {
  router.push(`/index/${table}Detail?id=${id}`)
}
const moreClick = (table) => {
  router.push(`/index/${table}List`)
}
const init = () => {
  //公告
  getNewsList()
}
init()
</script>

<style lang="scss">
.home_box {
}

// 推荐
.recomList_view {
  .recomList_title {
  }
  // list
  .recommend_list_three {
    display: flex;
    // 左边盒子
    .item_left {
      margin: 0 20px 0 0;
      width: calc(30% - 20px);
      // 左上
      .item_left_top {
        margin: 0 0 20px;
        width: 100%;
        position: relative;
        .recommend_img_box {
          font-size: 0;
          .recommend_img {
            object-fit: cover;
            width: 100%;
            height: 300px;
          }
        }
        .recommend_content {
          z-index: 1002;
          color: #fff;
          left: 0;
          bottom: 0;
          background: rgba(0, 0, 0, 0.3);
          width: 100%;
          line-height: 3;
          position: absolute;
          text-align: center;
          .recommend_title {
          }
        }
      }
      // 左下
      .item_left_bottom {
        width: 100%;
        position: relative;
        .recommend_img_box {
          font-size: 0;
          .recommend_img {
            object-fit: cover;
            width: 100%;
            height: 300px;
          }
        }
        .recommend_content {
          z-index: 1002;
          color: #fff;
          left: 0;
          bottom: 0;
          background: rgba(0, 0, 0, 0.3);
          width: 100%;
          line-height: 3;
          position: absolute;
          text-align: center;
          .recommend_title {
          }
        }
      }
    }
    // 中间
    .item_center {
      width: 40%;
      position: relative;
      .recommend_img_box {
        font-size: 0;
        .recommend_img {
          object-fit: cover;
          width: 100%;
          height: 620px;
        }
      }
      .recommend_content {
        z-index: 1002;
        color: #fff;
        left: 0;
        bottom: 0;
        background: rgba(0, 0, 0, 0.3);
        width: 100%;
        line-height: 3;
        position: absolute;
        text-align: center;
        .recommend_title {
        }
      }
    }
    // 右边盒子
    .item_right {
      // 右上
      .item_right_top {
        margin: 0 0 20px;
        width: 100%;
        position: relative;
        .recommend_img_box {
          font-size: 0;
          .recommend_img {
            object-fit: cover;
            width: 100%;
            height: 300px;
          }
        }
        .recommend_content {
          z-index: 1002;
          color: #fff;
          left: 0;
          bottom: 0;
          background: rgba(0, 0, 0, 0.3);
          width: 100%;
          line-height: 3;
          position: absolute;
          text-align: center;
          .recommend_title {
          }
        }
      }
      // 右下
      .item_right_bottom {
        width: 100%;
        position: relative;
        .recommend_img_box {
          font-size: 0;
          .recommend_img {
            object-fit: cover;
            width: 100%;
            height: 300px;
          }
        }
        .recommend_content {
          z-index: 1002;
          color: #fff;
          left: 0;
          bottom: 0;
          background: rgba(0, 0, 0, 0.3);
          width: 100%;
          line-height: 3;
          position: absolute;
          text-align: center;
          .recommend_title {
          }
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
  }
  // animation
  // 更多
  .recommend_more_view {
    .recommend_more_text {
    }
  }
}
// 推荐
// 新闻资讯
.newsList_view {
  .newsList_title {
  }
  // list
  .news_list_one {
    display: flex;
    flex-wrap: wrap;
    .news_item {
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
      margin: 0 10px 10px;
      background: #fff;
      display: flex;
      width: calc(33% - 20px);
      align-items: center;
      .news_img_box {
        width: 40%;
        font-size: 0;
        .news_img {
          object-fit: cover;
          width: 100%;
          height: 200px;
        }
      }
      .news_content {
        margin: 0 0 0 20px;
        width: calc(60% - 20px);
        .news_title {
          font-weight: bold;
          font-size: 20px;
        }
        .news_text {
          font-size: 14px;
          line-height: 1.5;
        }
        .news_time {
          color: #999;
          width: 100%;
          text-align: right;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .news_more_view {
    .news_more_text {
    }
    .el-icon {
    }
  }
}
// 新闻资讯
// 首页展示
.homeList_view {
  .homeList_title {
  }
  // list
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .homeList_more_view {
    cursor: pointer;
    .homeList_more_text {
    }
  }
}
// 首页展示
</style>
<style lang="scss">
.home_box {
  display: flex;
  flex-direction: column;
}

.homeList_view {
  order: 3;
}

.aboutUs_view {
  order: 8;
}

.recomList_view {
  order: 2;
}

.friendLink {
  order: 6;
}

.newsList_view {
  order: 4;
}

.systemInfo_view {
  order: 6;
}

.onlineMessage {
  order: 7;
}
.newsList_view {
  background-size: 100% 100%, 100% 100%;
  background-position: 30% 0, 30% 0;
  padding: 20px calc(50% - 700px) 50px;
  position: relative;
}

.newsList_view .newtitle {
  font-size: 22px;
  color: var(--theme);
  margin-bottom: 70px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.newsList_view .item0 img {
  object-fit: cover;
  position: absolute;
  left: 100px;
  top: 0;
  width: 420px;
  height: 420px;
  border-radius: 50%;
  opacity: 0.7;
}

.newsList_view .item {
  cursor: pointer;
  background: #fff;
  border-bottom: 1px dashed #d7d7d7;
  display: flex;
  width: 100%;
  align-items: center;
  height: 70px;
}

.newsList_view .list {
  display: grid;
  grid-template-columns: 430px 950px;
  gap: 15px 20px;
  color: #000;
}

.newsList_view .item0 .time {
  color: #000;
  white-space: nowrap;
  width: 120px;
  line-height: 30px;
  font-size: 16px;
  background: #fff;
}
.newsList_view .item .time {
  font-size: 16px;
  white-space: nowrap;
  padding: 0 18px;
}

.newsList_view .year {
  font-weight: 400;
  font-size: 12px;
  text-align: right;
  color: #999;
}

.newsList_view .item .title {
  font-size: 16px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  padding: 0 18px;
  flex: 1;
  width: 0;
}

.newsList_view .item .info {
  font-size: 14px;
  color: #999;
  width: 100%;
  margin: 20px 0;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  padding: 0 18px;
}

.newsList_view .item0 {
  position: relative;
  max-width: none;
  background: none;
  display: flex;
  column-gap: 20px;
  align-items: center;
  cursor: pointer;
  width: 471px;
  height: 92px;
  border: 1px solid #d7d7d7;
}

.newsList_view .item0 .info {
  margin: 20px 0;
  color: #999;
}

.newsList_view .item0 .title {
  font-size: 16px;
  line-height: 1.5em;
  color: #000;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.newsList_view .to {
  font-size: 30px;
  line-height: 10px;
  color: #999;
}

.newsList_view .nmore {
  width: 100px;
  background: var(--theme);
  text-align: center;
  border-radius: 20px;
  line-height: 30px;
  color: #fff;
  cursor: pointer;
  font-size: 14px;
}

.newsList_view .in {
  font-size: 24px;
  color: #3092ac;
  min-height: 50px;
  line-height: 50px;
  position: relative;
}

.newsList_view .imgbox {
  background: url(http://localhost:8080/coffeeShopSeatReserva/file/358d2f563cfe4fc699ed9587d65db8e9.jpg);
  background-size: 100% 100%;
  width: 92px;
  height: 100%;
  flex-shrink: 0;
}

.newsList_view .item0 .infobox {
  z-index: 1;
  flex: 1;
  width: 0;
}

.newsList_view .item img {
  object-fit: cover;
  border-radius: 0;
  width: 346px;
  height: 231px;
}

.newsList_view span.time_in {
  line-height: 30px;
  position: relative;
  display: inline-block;
}

.newsList_view .in:after {
  position: absolute;
  content: '';
  width: 210px;
  height: 17px;
  background: linear-gradient(
    271deg,
    rgba(216, 216, 216, 0) 0%,
    RGBA(190, 223, 220, 1) 60%,
    #5bbcb4 100%
  );
  border-radius: 10px 10px 10px 10px;
  bottom: -14px;
  left: 0;
}

.newsList_view .item1 {
  grid-area: 1/2/5/3;
  display: flex;
  align-items: center;
  width: 950px;
}

.newsList_view img {
  width: 460px;
  height: 325px;
  margin-right: 20px;
  flex-shrink: 0;
  object-fit: cover;
}

.newsList_view .title {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 16px;
}

.newsList_view .infobox {
  width: 0;
  flex: 1;
}

.newsList_view .intro {
  margin-top: 30px;
  color: rgba(132, 132, 132, 1);
  display: -webkit-box;
  -webkit-line-clamp: 5;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  font-size: 16px;
  line-height: 1.8;
}

.newsList_view .item1 .time {
  margin-top: 30px;
  font-size: 16px;
}

.newsList_view .n1 {
  width: 70px;
  line-height: 30px;
  background: var(--theme);
  text-align: center;
  color: #fff;
}

.newsList_view .item:hover {
  color: var(--theme);
}
</style>
