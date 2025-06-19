const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '论坛交流',
					icon: '',
					child:[

						{
							name:'论坛',
                            url:'/index/forumList'

						},
					]
				},
				{
					name: '自习室管理',
					icon: '',
					child:[

						{
							name:'咖啡厅馆座位',
                            url:'/index/kafeitingguanzuoweiList'

						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: `基于springboot + vue的咖啡厅座位预约平台`
        } 
    }
}
export default config
