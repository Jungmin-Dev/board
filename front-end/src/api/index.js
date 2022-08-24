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


export const requestFileDownLoad = (method , url , data) =>{
  return axios({
    method,
    url : DOMAIN + url,
    data,
    responseType: "blob"
  }).then((res)=>{
    return res.data
  }).catch( res=>{
    throw res.response.data;
  });
}
