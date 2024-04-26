FROM node:21.7.1 AS build-stage

WORKDIR /app

COPY . .

EXPOSE 8000

CMD ["sh", "-c", "npm install ; npm run dev"]