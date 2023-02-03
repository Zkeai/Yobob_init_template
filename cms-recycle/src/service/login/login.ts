import hyRequest from '..'
import type { IAccount, IRegAccount } from '@/types'
export function loginRequest(loginParam: IAccount) {
  return hyRequest.post({
    url: '/api/user/login',
    data: loginParam
  })
}

export function registerRequest(registerParam: IRegAccount) {
  return hyRequest.post({
    url: '/api/user/register',
    data: registerParam,
    headers: {
      Authorization: 'Bearer ' + localStorage.getItem('token')
    }
  })
}
