<!-- 博客列表 -->

<template>
    <div class="full_page">
        <img class="header_bg" src="https://mr-luowan.github.io/img/whale.webp" alt="">
    </div>
    <div class="header">
        <Header></Header>
    </div>

    <div class="outer">
        <div class="main_container">
            <div class="article_con" v-for="(article, index) in articles" :key="index">
                <div class="article_item">
                    <div class="artile_cover" :style="{'background-image':'url(' + article.cover + ')'}"></div>
                    <div class="article_info">
                            <span class="title" @click="goDetail(article.id)">{{ article.title }}</span>
                            <div class="create_time">
                                <svg t="1680596232257" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1586" width="16" height="16" xmlns:xlink="http://www.w3.org/1999/xlink"><path d="M883.278513 148.539035l-43.016723 0 0-12.780071c0-38.310537-31.199583-69.632917-69.323878-69.632917l-1.02433 0c-38.129412 0-69.323878 31.321356-69.323878 69.632917l0 12.780071L317.20292 148.539035l0-12.780071c0-38.310537-31.195489-69.632917-69.323878-69.632917l-1.055029 0c-38.127366 0-69.322855 31.321356-69.322855 69.632917l0 12.780071-42.924625 0c-38.127366 0-69.323878 31.351032-69.323878 69.609381l0 665.701614c0 38.309514 31.195489 69.632917 69.323878 69.632917l748.70198 0c38.127366 0 69.322855-31.323403 69.322855-69.632917l0-665.701614C952.601368 179.890067 921.405879 148.539035 883.278513 148.539035L883.278513 148.539035zM728.318232 135.758963c0-23.024389 18.632359-41.777499 41.594327-41.777499l1.02433 0c22.931269 0 41.593304 18.753109 41.593304 41.777499L812.530193 259.615852c0 23.024389-18.662035 41.778522-41.593304 41.778522l-1.02433 0c-22.961968 0-41.594327-18.754133-41.594327-41.778522L728.318232 135.758963 728.318232 135.758963zM205.23071 135.758963c0-23.024389 18.632359-41.777499 41.599444-41.777499l1.048889 0c22.903639 0 41.594327 18.753109 41.594327 41.777499L289.473369 259.615852c0 23.024389-18.691711 41.778522-41.594327 41.778522l-1.048889 0c-22.967084 0-41.599444-18.754133-41.599444-41.778522L205.23071 135.758963 205.23071 135.758963zM883.278513 897.807926l-748.70198 0c-7.519254 0-13.864776-6.468318-13.864776-13.957897L120.711757 394.394489l776.433578 0 0 489.455541C897.144312 891.338584 890.797767 897.807926 883.278513 897.807926L883.278513 897.807926zM222.614635 494.083955l127.918391 0 0 62.950727L222.614635 557.034682 222.614635 494.083955 222.614635 494.083955zM445.963493 494.083955l126.906342 0 0 62.950727L445.963493 557.034682 445.963493 494.083955 445.963493 494.083955zM668.299277 494.083955l124.873032 0 0 62.950727L668.299277 557.034682 668.299277 494.083955 668.299277 494.083955zM222.614635 622.00951l127.918391 0 0 61.927421L222.614635 683.936931 222.614635 622.00951 222.614635 622.00951zM445.963493 622.00951l126.906342 0 0 61.927421L445.963493 683.936931 445.963493 622.00951 445.963493 622.00951zM668.299277 622.00951l124.873032 0 0 61.927421L668.299277 683.936931 668.299277 622.00951 668.299277 622.00951zM222.614635 746.884588l127.918391 0 0 64.97278L222.614635 811.857369 222.614635 746.884588 222.614635 746.884588zM445.963493 746.884588l126.906342 0 0 64.97278L445.963493 811.857369 445.963493 746.884588 445.963493 746.884588zM668.299277 746.884588l124.873032 0 0 64.97278L668.299277 811.857369 668.299277 746.884588 668.299277 746.884588zM668.299277 746.884588" fill="#707070" p-id="1587"></path></svg>
                                <span>&nbsp;发布于 {{ article.createTime }}</span>
                            </div>
                            <div class="digst">{{ article.content }}</div>
                    </div>
                </div>
            </div> 
            <div class="page_util">
                <el-pagination background layout="prev, pager, next" 
                    :total=this.total
                    :page-size=pageSize
                    @current-change="handleCurrentChange "/>
            </div>
        </div>
        
        <div class="user_card">
            <img class="avatar" src="../assets/img/avatar.jfif" alt="">
            <span class="author_name" v-text="author"></span>
            <div class="signal_info" v-text="signal"></div>
            <div class="article_count_info">
                <div class="label_info">文章</div>
                <div class="label_info">标签</div>
                <div class="label_info">分类</div>
                <div class="label_info" v-text="articles.length"></div>
                <div class="label_info">5</div>
                <div class="label_info">4</div>
            </div>
            <div class="outer_link" @click="goAuthorLink">
                <i class="far fa-user"></i>
                <span>&nbsp;&nbsp;作者github</span>
            </div>
        </div>
    </div>
        <div v-if="btnFlag" class="go-top" @click="backTop">
            <img class="back_png" src="..\assets\img\go_top.png" alt="">
        </div>
</template>

<script>
    import Header from '../components/Header.vue'
    import http from '../axiosdir'
    export default {
        name: 'Blogs',
        components: {Header},
        data() {
            return {
                articles: {},
                currentPage: 1,
                total: 0,
                pageSize: 3,
                btnFlag: false,
                scrollTop: 0,
                author: "Mr_Lee",
                signal: "这个地方是个性签名,这个地方是个性签名."
            }
        },
        methods: {
            js_biao_year(time) {
                var date = new Date(time)
                var y = date.getFullYear()
                var m = date.getMonth() + 1
                m = m < 10 ? ('0' + m) : m
                var d = date.getDate()
                d = d < 10 ? ('0' + d) : d
                var h = date.getHours()
                h = h < 10 ? ('0' + h) : h
                var minute = date.getMinutes()
                minute = minute < 10 ? ('0' + minute) : minute
                var s = date.getSeconds()
                s = s < 10 ? ('0' + s) : s
                return y + '-' + m + '-' + d
            },
            handleCurrentChange(number) {
                this.page(number)
            },
            page(currentPage) {
                const _this = this
                http(
                    {   url:'/api/article/articles?pageNum=' + currentPage + '&pageSize=' + this.pageSize,
                        headers: {
                            isToken: true
                        },
                        method: "get" }
                ).then((res) => {
                    _this.articles = res.data.data.records
                    _this.total = res.data.data.total
                    for(let index in _this.articles) {
                        _this.articles[index].content = _this.articles[index].content.substring(0,100) + "..."
                        let createdTime = _this.articles[index].createTime
                        let date = _this.js_biao_year(createdTime)
                        _this.articles[index].createTime = date
                        console.log('单篇文章创建时间 ',  createdTime)
                    }
                })
            },
            goDetail(blogId) {
                this.$router.push({ name: 'BlogDetail', params: {'blogId': blogId} })
            },
            addMouseListener() {
            },
            backTop() {
                const _this = this;
                let timer = setInterval(()=> {
                    let ispeed = Math.floor(-_this.scrollTop / 5);
                    document.documentElement.scrollTop = document.body.scrollTop = _this.scrollTop + ispeed;
                    if (_this.scrollTop === 0)
                    {
                        clearInterval(timer);
                    }
                }, 16);
            },
            scrollToTop() {
                const _this = this;
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
                _this.scrollTop = scrollTop;
                if (_this.scrollTop > 0)
                {
                    _this.btnFlag = true;
                } else {
                    _this.btnFlag = false;
                }
            },
            goAuthorLink () {
                // var nativeRet = window.jsBridge.jsCallAndroidMethod();
                // console.log("来自安卓的回复 = ", nativeRet.age)
                window.open("https://github.com/Mr-Luowan", "_blank");
            }
        },
        mounted () {
            this.page(1);
            this.addMouseListener();
            window.addEventListener('scroll', this.scrollToTop);
        },
        deactivated() {
            window.removeEventListener('scroll', this.scrollToTop)
        }

    }

</script>

<style>
:root {
    --info_width:300px;
}
    .outer {
        width: 100%;
        display:flex;
        padding-bottom: 80px;
        justify-content: center;
    }
    .main_container {
        background-color: transparent;
    }
    
    .article_con {
        width: 900px;
    }
    
    .article_item {
        display: flex;
        justify-content:center;
        height: 280px;
        margin-top: 35px;
        border-radius: 10px;
        box-shadow: 0 3px 8px 6px rgba(7,17,27,0.05);
    }
    .article_con:nth-child(odd)
        >.article_item{
        flex-direction: row;
        }
    .article_con:nth-child(even)
        >.article_item{
        flex-direction: row-reverse;
        }
        
        
    .article_con:nth-child(odd)
    .article_item
        >.artile_cover {
            border-top-left-radius: 10px;
            border-bottom-left-radius: 10px;
        }
    .article_con:nth-child(even)
    .article_item
        >.artile_cover {
            border-top-right-radius: 10px;
            border-bottom-right-radius: 10px;
        }

    .artile_cover {
        background-repeat: no-repeat;
        background-size: 100% 100%;
        width: 600px;
        height: 280px;
        transition: all 1.0s ease-in-out;
    }
    
    .artile_cover:hover {
        background-size: 105% 105%;
        cursor: pointer;
    }
    
    .create_time {
        display: flex;
        align-items: center;
        margin-top: 10px;
    }

    .article_info {
        display: inline-flex;
        flex-direction: column;
        justify-content: center;
        margin-left: 50px;
        margin-right: 50px;
    }
    .title {
        cursor: pointer;
        font-weight: bold;
        font-size: 1.43em;
    }
    .digst {
        margin-top: 10px;
    }
    .user_card {
        margin-top: 35px;
        margin-left: 20px;
        width: var(--info_width);
        height: 360px;
        border-radius: 16px;
        padding: 16px;
        display: flex;
        flex-direction: column;
        align-items: center;
        box-shadow: 0 3px 8px 6px rgba(7,17,27,0.05);
    }
    .avatar {
        border-radius: 50%;
        width: 100px;
        height: 100px;
        display: block;
        transition: filter 375ms ease-in 0.2s, transform 0.3s;
        object-fit: cover;
    }
    .avatar:hover{
        transform:rotate(360deg);
    }
    .author_name {
        display: block;
        font-weight: 500;
        font-size: 1.57em;
    }
    .signal_info {
        font-weight: 400;
        padding: 10px 16px;
    }
    .outer_link {
        width: 220px;
        height: 40px;
        background-color: var(--main_color);
        border-radius: 5px;
        display: flex;
        justify-content: center;
        align-items: center;
        color: white;
    }
    .outer_link:hover {
        background-color: var(--theme_orange);
    }
    .article_count_info {
        display: grid;
        width:calc(var(--info_width) - 60px);
        margin-top: 20px;
        grid-template-columns: 1fr 1fr 1fr;
        grid-template-rows: 25px 25px 25px;
    }
    .label_info {
        text-align: center;
    }
    .page_util {
        margin-top: 30px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .go-top {
        width: 60px;
        height: 60px;
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: transparent;
    }
    .back_png {        
        border-radius: 50%;
    }
    .full_page {
        flex-direction: column;
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 100vh;
    }
    .header {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
    }
    .header_bg {
        width: 100%;
        height: 100%;
    }
</style>