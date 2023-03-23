import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'

import type { UserConfig, ConfigEnv } from 'vite'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import {
  createStyleImportPlugin,
  ElementPlusResolve
} from 'vite-plugin-style-import'
import vue from '@vitejs/plugin-vue'

export default defineConfig(({ mode }: ConfigEnv): UserConfig => {
  const env = loadEnv(mode, process.cwd(), '')

  return {
    plugins: [
      vue(),
      AutoImport({
        resolvers: [ElementPlusResolver()]
      }),
      Components({
        resolvers: [ElementPlusResolver()]
      }),
      createStyleImportPlugin({
        resolves: [ElementPlusResolve()],
        libs: [
          {
            libraryName: 'element-plus',
            esModule: true,
            resolveStyle: (name: string) => {
              return `element-plus/theme-chalk/${name}.css`
            }
          }
        ]
      })
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    server: {
      open: true,
      host: 'localhost',
      port: env.VITE_PORT as unknown as number,
      proxy: {
        '/api': {
          target: env.VITE_BASE_API,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    }
  }
})

// https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [
//     vue(),
//     AutoImport({
//       resolvers: [ElementPlusResolver()]
//     }),
//     Components({
//       resolvers: [ElementPlusResolver()]
//     }),
//     createStyleImportPlugin({
//       resolves: [ElementPlusResolve()],
//       libs: [
//         {
//           libraryName: 'element-plus',
//           esModule: true,
//           resolveStyle: (name: string) => {
//             return `element-plus/theme-chalk/${name}.css`
//           }
//         }
//       ]
//     })
//   ],
//   resolve: {
//     alias: {
//       '@': fileURLToPath(new URL('./src', import.meta.url))
//     }
//   },
//   server: {
//     open: true,
//     host: 'localhost',
//     port: 5173,
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8080/api',
//         changeOrigin: true,
//         rewrite: (path) => path.replace(/^\/api/, '')
//       }
//     }
//   }
// })
