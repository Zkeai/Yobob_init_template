import { CACHETOKEN } from '@/global/cache-constants'
import hyRequest from '@/service/index'
import { localCache } from '@/utils/localCache'
//通用获取
export function postPageListRequest(pageName: string, queryData: any) {
  return hyRequest.post({
    url: `/api/${pageName}/list `,
    data: queryData,
    headers: {
      Authorization: 'Bearer ' + localStorage.getItem('token')
    }
  })
}

//通用删除
export function deletePageRequest(pageName: string, id: number) {
  return hyRequest.delete({
    url: `/api/${pageName}/delete/` + id,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

//通用新增或修改
export function addOrSavePageRequest(pageName: string, userInfo: any) {
  return hyRequest.post({
    url: `/api/${pageName}/saveOrUpdate`,
    data: userInfo,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}
//通用根据id修改状态
export function updateIsBanPageRequest(pageName: string, userInfo: any) {
  return hyRequest.post({
    url: `/api/${pageName}/updateIsBan`,
    data: userInfo,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}
