import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
dayjs.extend(utc)
function timeFormat(time: string) {
  return dayjs(new Date(time)).format('YYYY-MM-DD HH:mm:ss')
}

export function getTime(timeArray: string) {
  let time = ''
  const createTime1 = timeFormat(timeArray[0])
  const createTime2 = timeFormat(timeArray[1])
  time = createTime1 + '`' + createTime2
  return time
}

export function formatUTC(UtcTimeString: string) {
  return dayjs.utc(UtcTimeString).format('YYYY-MM-DD HH:mm:ss')
}
