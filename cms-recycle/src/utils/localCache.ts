enum CacheType {
  Local,
  Session
}

class Cache {
  storage: Storage
  constructor(type: CacheType) {
    this.storage = type === CacheType.Local ? localStorage : sessionStorage
  }
  setCache(key: string, value: any) {
    if (value) {
<<<<<<< HEAD
      this.storage.setItem(key, value)
=======
      this.storage.setItem(key, JSON.stringify(value))
>>>>>>> 587c8a61070bff41b665e566ce3fbfc8578fe7e5
    }
  }

  getCache(key: string) {
    const value = this.storage.getItem(key)
    if (value) {
<<<<<<< HEAD
      return value
=======
      return JSON.parse(value)
>>>>>>> 587c8a61070bff41b665e566ce3fbfc8578fe7e5
    }
  }

  removeCache(key: string) {
    this.storage.removeItem(key)
  }

  clear() {
    this.storage.clear()
  }
}

const localCache = new Cache(CacheType.Local)

const sessionCache = new Cache(CacheType.Session)

export { localCache, sessionCache }
