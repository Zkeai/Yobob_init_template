import type { IAddOrUpdateInfo, IUser } from '@/types/user'
import { localCache } from '@/utils/localCache'
import hyRequest from '..'
import { CACHETOKEN } from '@/global/cache-constants'

export function userListRequest(userParam: IUser) {
  return hyRequest.post({
    url: '/api/user/list',
    data: userParam,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

export function deleteUserRequest(id: number) {
  return hyRequest.delete({
    url: '/api/user/emp/delete/' + id,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

export function addOrSaveUserRequest(userInfo: IAddOrUpdateInfo) {
  return hyRequest.post({
    url: '/api/user/emp/saveOrUpdate',
    data: userInfo,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}
