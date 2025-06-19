import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import newsList from '@/views/pages/news/list'
import forumList from '@/views/pages/forum/list'
import kafeitingguanzuoweiList from '@/views/pages/kafeitingguanzuowei/list'
import kafeitingguanzuoweiDetail from '@/views/pages/kafeitingguanzuowei/formModel'
import kafeitingguanzuoweiAdd from '@/views/pages/kafeitingguanzuowei/formAdd'
import yuyuejiluList from '@/views/pages/yuyuejilu/list'
import yuyuejiluDetail from '@/views/pages/yuyuejilu/formModel'
import yuyuejiluAdd from '@/views/pages/yuyuejilu/formAdd'
import yuyuequxiaoList from '@/views/pages/yuyuequxiao/list'
import yuyuequxiaoDetail from '@/views/pages/yuyuequxiao/formModel'
import yuyuequxiaoAdd from '@/views/pages/yuyuequxiao/formAdd'
import syslogList from '@/views/pages/syslog/list'
import syslogDetail from '@/views/pages/syslog/formModel'
import syslogAdd from '@/views/pages/syslog/formAdd'
import qiandaoxinxiList from '@/views/pages/qiandaoxinxi/list'
import qiandaoxinxiDetail from '@/views/pages/qiandaoxinxi/formModel'
import qiandaoxinxiAdd from '@/views/pages/qiandaoxinxi/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'kafeitingguanzuoweiList',
			component: kafeitingguanzuoweiList
		}, {
			path: 'kafeitingguanzuoweiDetail',
			component: kafeitingguanzuoweiDetail
		}, {
			path: 'kafeitingguanzuoweiAdd',
			component: kafeitingguanzuoweiAdd
		}
		, {
			path: 'yuyuejiluList',
			component: yuyuejiluList
		}, {
			path: 'yuyuejiluDetail',
			component: yuyuejiluDetail
		}, {
			path: 'yuyuejiluAdd',
			component: yuyuejiluAdd
		}
		, {
			path: 'yuyuequxiaoList',
			component: yuyuequxiaoList
		}, {
			path: 'yuyuequxiaoDetail',
			component: yuyuequxiaoDetail
		}, {
			path: 'yuyuequxiaoAdd',
			component: yuyuequxiaoAdd
		}
		, {
			path: 'syslogList',
			component: syslogList
		}, {
			path: 'syslogDetail',
			component: syslogDetail
		}, {
			path: 'syslogAdd',
			component: syslogAdd
		}
		, {
			path: 'qiandaoxinxiList',
			component: qiandaoxinxiList
		}, {
			path: 'qiandaoxinxiDetail',
			component: qiandaoxinxiDetail
		}, {
			path: 'qiandaoxinxiAdd',
			component: qiandaoxinxiAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
	,{
		path: '/forget',
		name: 'forget',
		component: () => import('../views/pages/forget.vue')
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
