FROM nginx:alpine

RUN apk update && apk add --no-cache tzdata

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY dist/balance-web/ /usr/share/nginx/html/
