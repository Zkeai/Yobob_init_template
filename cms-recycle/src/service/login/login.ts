import hyRequest from '..'

export function loginRequest(account: any) {
  return hyRequest.post({
    url: '/api/user/login',
    data: account
  })
}
