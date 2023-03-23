let BASE_URL = ''
const SERVICE_BASE_URL = import.meta.env.VITE_SERVICE_BASE_URL
if (import.meta.env.PROD) {
  BASE_URL = SERVICE_BASE_URL
} else {
  BASE_URL = SERVICE_BASE_URL
}

// 3.通过创建.env文件直接创建变量

export const TIME_OUT = import.meta.env.VITE_SERVICE_TIMEOUT
export { BASE_URL }
