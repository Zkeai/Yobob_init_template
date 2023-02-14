import { CACHETOKEN } from '@/global/cache-constants'
import { localCache } from '@/utils/localCache'
import hyRequest from '..'

export function authenticationRequest() {
  return hyRequest.get({
    url: '/api/token/authentication',
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

export function getRolesRequest() {
  return hyRequest.get({
    url: '/api/role/listAll',
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

export function getDeparmentsRequest() {
  return hyRequest.get({
    url: '/api/department/listAll',
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}

export function getPostsRequest() {
  return hyRequest.get({
    url: '/api/post/listAll',
    headers: {
      authorization: 'Bearer ' + localCache.getCache(CACHETOKEN)
    }
  })
}
