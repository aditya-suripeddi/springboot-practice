# Multi-Stage build

# Install dependencies in a cacheable unit / layer
#  (usually depends don't change frequently, we keep at first layer)
FROM node:16-alpine AS deps
RUN apk add --no-cache libc6-compat
WORKDIR /app
COPY package.json yarn.lock ./
RUN yarn install --frozen-lock

# Rebuild the source code in a consistent environment and cache it
FROM node:16-alpine AS builder
WORKDIR /app
COPY . .
COPY --from=deps /app/node_modules ./node_modules
RUN yarn build

# Production image, copy all files and run next
FROM node:16-alpine AS runner
WORKDIR /app

ENV NODE_ENV production

# revisit the need for adding group and user
# https://stackoverflow.com/questions/68155641/should-i-run-things-inside-a-docker-container-as-non-root-for-safety
RUN addgroup -g 1001 -S nodejs
RUN adduser -S nextjs -u 1001

# only if default next.config.js configuration is NOT used, it is required to copy it
COPY --from=builder /app/next.config.js  ./
COPY --from=builder /app/public   ./public
COPY --from=builder --chown=nextjs:nodejs /app/.next  ./.next
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/package.json ./package.json

USER nextjs

EXPOSE 3000
CMD ["yarn", "start"]