import type { IPageParam } from '@/types'
import hyRequest from '..'

export function getDepartmentPageRequest(pageParam: IPageParam) {
  return hyRequest.get({
    url:
      '/api/department/list/page?pageNum=' +
      pageParam.pageNum +
      '&pageSize=' +
      pageParam.pageSize,
    headers: {
      Authorization: 'Bearer ' + localStorage.getItem('token')
    }
  })
}
