import hyRequest from '..'
import qs from 'qs'
import type { IAccount, IRegAccount } from '@/types'
export function loginRequest(loginParam: IAccount) {
  return hyRequest.post({
    url: '/api/login',
    data: qs.stringify(loginParam),
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
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
