import axios from 'axios'
import { DOMAIN } from "@/Constants";

export const request = (method , url , data) =>{
  return axios({
    method,
    url : DOMAIN + url,
    data
  }).then((res)=>{
    return res.data
  }).catch( res=>{
    throw res.response.data;
  });
}


export const requestImage = (method , url , data) =>{
  return axios({
    method,
    url : DOMAIN + url,
    data,
    headers:{
      "Content-Type": "multipart/form-data",
    }
  }).then((res)=>{
    return res.data
  }).catch( res=>{
    throw res.response.data;
  });
}
