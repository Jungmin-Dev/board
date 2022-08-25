import axios from 'axios'
import { DOMAIN } from "@/Constants";

export const request = (method , url , data) =>{
  return axios({
    method,
    url : DOMAIN + url,
    data,

  }).then((res)=>{
    return res.data
  }).catch( res=>{
    throw res.response.data;
  });
}

// 파일 다운로드 요청
export const requestFileDownLoad = (method , url , data) =>{
  return axios({
    method,
    url : DOMAIN + url,
    data,
    responseType: "blob"
  }).then((res)=>{
    const url = window.URL.createObjectURL(new Blob([res.data], { type: res.data.type }))

    const link = document.createElement('a')

    link.href = url

    link.setAttribute('download', data.fileName)

    document.body.appendChild(link)

    link.click()

  }).catch( res=>{
    throw res.response.data;
  });
}
