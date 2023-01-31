import hyRequest from '..'
import type { IAccount, IRegAccount } from '@/types'
export function loginRequest(account: IAccount) {
  return hyRequest.post({
    url: '/api/user/login',
    data: account,
    headers: {
      token: '3243535'
    }
  })
}

export function registerRequest(account: IRegAccount) {
  return hyRequest.post({
    url: '/api/user/register',
    data: account,
    headers: {
      Authorization: 'Bearer ' + localStorage.getItem('token')
    }
  })
}
