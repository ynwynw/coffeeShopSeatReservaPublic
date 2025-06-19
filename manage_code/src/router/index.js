import { createRouter, createWebHashHistory } from 'vue-router'
import news from '@/views/news/list'
import forum from '@/views/forum/list'
import kafeitingguanzuowei from '@/views/kafeitingguanzuowei/list'
import discusskafeitingguanzuowei from '@/views/discusskafeitingguanzuowei/list'
import yonghu from '@/views/yonghu/list'
import yuyuejilu from '@/views/yuyuejilu/list'
import qiandaoxinxi from '@/views/qiandaoxinxi/list'
import syslog from '@/views/syslog/list'
import menu_manage from '@/views/menu_manage/list'
import config from '@/views/config/list'
import yuyuequxiao from '@/views/yuyuequxiao/list'
import users from '@/views/users/list'

export const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login.vue'),
  },
  {
    path: '/',
    name: '首页',
    component: () => import('../views/index'),
    children: [
      {
        path: '/',
        name: '首页',
        component: () => import('../views/HomeView.vue'),
        meta: {
          affix: true,
        },
      },
      {
        path: '/updatepassword',
        name: '修改密码',
        component: () => import('../views/updatepassword.vue'),
      },

      {
        path: '/news',
        name: 'news',
        component: news,
      },
      {
        path: '/forum',
        name: 'forum',
        component: forum,
      },
      {
        path: '/kafeitingguanzuowei',
        name: 'kafeitingguanzuowei',
        component: kafeitingguanzuowei,
      },
      {
        path: '/discusskafeitingguanzuowei',
        name: 'discusskafeitingguanzuowei-评论',
        component: discusskafeitingguanzuowei,
      },
      {
        path: '/yonghu',
        name: 'yonghu',
        component: yonghu,
      },
      {
        path: '/yuyuejilu',
        name: 'yuyuejilu',
        component: yuyuejilu,
      },
      {
        path: '/qiandaoxinxi',
        name: 'qiandaoxinxi',
        component: qiandaoxinxi,
      },
      {
        path: '/syslog',
        name: 'syslog',
        component: syslog,
      },
      {
        path: '/menu',
        name: '菜单权限管理',
        component: menu_manage,
      },
      {
        path: '/config',
        name: 'config',
        component: config,
      },
      {
        path: '/yuyuequxiao',
        name: 'yuyuequxiao',
        component: yuyuequxiao,
      },
      {
        path: '/users',
        name: 'users',
        component: users,
      },
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes,
})

export default router
