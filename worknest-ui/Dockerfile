# versione node usata da fabio 21.7.1
FROM node:21.7.1 AS build-stage
WORKDIR /app
# COPY package*.json ./app
# RUN npm install
COPY . .
# RUN chmod +x node_modules/.bin/vite
# RUN npm run build
# RUN npm run dev

EXPOSE 8000
# CMD ["npm", "install", "npm", "run", "dev"]
CMD ["sh", "-c", "npm install ; npm run dev"]