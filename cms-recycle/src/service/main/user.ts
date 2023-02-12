import type { IUser } from '@/types/user'
import { localCache } from '@/utils/localCache'
import hyRequest from '..'
import { CACHETOKEN } from '@/global/cache-constants'

export function userListByPageRequest(userParam: IUser) {
  return hyRequest.post({
    url: '/api/user/list/page',
    data: userParam,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

export function userListRequest(userParam: IUser) {
  return hyRequest.post({
    url: '/api/user/list',
    data: userParam,
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}
