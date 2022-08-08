import {request} from "@/api";

const Content ={
  namespaced: true,

  state:{
    contents: null,
    contentDetail: null,
  },
  getters : {

  },

  mutations : {
    contentListUpdate(state, payload){
      state.contents = payload;
    },
    contentDetailList(state, payload){
      state.contentDetail = payload;
    }
  },
  actions : {
    // 게시글 불러오기
    async contentList({commit}){
    const Check = await request('post', '/content/list');
    await commit("contentListUpdate", Check.contentList);
    },

    // 게시글 자세히 보기(게시글 내용 불러오기)
    async contentDetailLoad({commit}, payload){
      const Check = await request('get', `/content/detail/${payload}`)
      await commit("contentDetailList", Check.contentList);
    },

    // 게시글 작성
    async contentInsert({commit}, payload){
      const Check = await request('post', `/content/insert`, payload)
      await this.$router.push({
        path: '/board-page'
      })
    }
  }
}

export default Content;
