FROM postgres:alpine

RUN apk update && apk add --no-cache tzdata

ENV POSTGRES_DB=balance_db
ENV POSTGRES_USER=balance_usr
ENV POSTGRES_PASSWORD=balance_pass

EXPOSE 5432/tcp