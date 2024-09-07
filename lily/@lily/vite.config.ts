import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

interface Args {
    command: string
}

const base = defineConfig({
    plugins: [
        vue(),
        vueJsx(),
    ],
    resolve: {
        alias: {
            '@basic': fileURLToPath(new URL('./packages/basic/src', import.meta.url)),
            '@violet': fileURLToPath(new URL('./packages/violet/src', import.meta.url)),
            '@fab': fileURLToPath(new URL('./packages/fab/src', import.meta.url)),
            'jquery-ui-esm': '@eirslett/jquery-ui-esm/esm',
        }
    },
    server: {
        fs: {
            // Allow serving files from one level up to the project root
            allow: ['.',
                '/'],
        },
    }
})

const build = ({ command }: Args) => defineConfig({
    root: './test',
    base: './',
    build: {
        target: command === 'serve' ? 'esnext' : 'es2015',
        // minify: command === 'serve' ? false : 'terser',
        outDir: '../dist',
        emptyOutDir: true,
    }
})

// https://vitejs.dev/config/
export default ({ command }: Args) => defineConfig({
    ...base,
    ...build({ command }),
})
