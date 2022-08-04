import {request} from "@/api";

const Content ={
  namespaced: true,

  state:{
    contents: null,
  },
  getters : {

  },

  mutations : {
    contentListUpdate(state, payload){
      state.contents = payload;
    }
  },
  actions : {
    // 게시글 불러오기
    async contentList({commit}){
    const Check = await request('post', '/content/list');
    console.log(Check.contentList)
    commit("contentListUpdate", Check.contentList);
    }
  }
}

export default Content;
