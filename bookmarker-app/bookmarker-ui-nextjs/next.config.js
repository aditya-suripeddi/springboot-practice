/** @type {import('next').NextConfig} */


// https://nextjs.org/docs/app/api-reference/next-config-js/runtime-configuration

const nextConfig = {
  reactStrictMode: true,

  // following setting was set in playlist, out of box
  // nextJS did not set it for me: (can be checked for default value using console.log)
  // https://nextjs.org/docs/architecture/nextjs-compiler#minification
  // swcMinify: true,

  serverRuntimeConfig: {
    API_BASE_URL: process.env.SERVER_SIDE_API_BASE_URL  // configured in docker-app.yaml
  },

  publicRuntimeConfig: {
    API_BASE_URL: process.env.CLIENT_SIDE_API_BASE_URL // configured in docker-app.yaml
  },

  async redirects() {
    return [
      {
        source : "/",
        destination: "/bookmarks",
        permanent: true
      }
    ]
  }
}

module.exports = nextConfig
