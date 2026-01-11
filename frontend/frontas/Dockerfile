# ---- Build stage ----
FROM node:22-alpine AS builder
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build


# ---- Run stage ----
FROM node:22-alpine AS runner
WORKDIR /app

ENV NODE_ENV=production

# Kopiujemy tylko to, co potrzebne do uruchomienia
COPY --from=builder /app/package*.json ./
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public
COPY --from=builder /app/next.config.* ./

# Bezpieczny user
RUN addgroup -S app && adduser -S app -G app
USER app

EXPOSE 3000

CMD ["npm", "run", "start"]
