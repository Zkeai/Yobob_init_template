import SparkMD5 from 'spark-md5'

// 获取文件的唯一MD5标识码
export function getFileMd5(file: Blob) {
  return new Promise((resolve) => {
    const fileReader = new FileReader()
    const spark = new SparkMD5.ArrayBuffer()
    fileReader.readAsArrayBuffer(file)
    fileReader.onload = (e) => {
      spark.append(e.target?.result as any)
      const md5 = spark.end()
      resolve(md5)
    }
  })
}
