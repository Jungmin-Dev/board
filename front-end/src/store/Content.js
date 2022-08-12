import {request} from "@/api";

const Content ={
  namespaced: true,

  state:{
    contents: null,
    contentDetail: null,
    contentComment: null,
    contentCommentSub: null,
  },
  getters : {

  },

  mutations : {
    contentList(state, payload){
      state.contents = payload;
    },
    contentDetailList(state, payload){
      state.contentDetail = payload;
    },
    contentCommentList(state, payload){
      state.contentComment = payload;
    },
    contentCommentSubList(state, payload){
      state.contentCommentSub = payload;
    }
  },
  actions : {
    // 게시글 불러오기
    async contentList({commit}){
    const Check = await request('post', '/content/list');
    await commit("contentList", Check.contentList);
    },

    // 게시글 자세히 보기(게시글 내용 불러오기)
    async contentDetailLoad({commit}, payload){
      const Check = await request('get', `/content/detail/${payload}`)
      await commit("contentDetailList", Check.contentList);
    },

    // 게시글 댓글 불러오기
    async contentCommentLoad({commit}, payload){
      const Check = await request('get', `/content/comment/${payload}`)
      await commit("contentCommentList", Check.contentCommentList)
      console.log(Check.contentCommentList)
    },

    // 게시글 대댓글 불러오기
    async contentCommentSubLoad({commit}, payload){
      const Check = await request('get', `/content/commentsub/${payload}`)
      await commit("contentCommentSubList", Check.contentCommentSubList)

    },
    // 게시글 작성
    async contentInsert({commit}, payload){
      await request('post', '/content/insert', payload)
    },
    // 게시글 삭제
    async contentContentDelete({commit}, payload){
      await request('get', `/content/delete/${payload}`)
    },

  }
}

export default Content;
