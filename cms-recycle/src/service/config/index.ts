let BASE_URL = ''
if (import.meta.env.PROD) {
  BASE_URL = 'https://backend.shuotian.vip'
} else {
  BASE_URL = 'http://localhost:5173'
}

// 3.通过创建.env文件直接创建变量

export const TIME_OUT = 10000
export { BASE_URL }
