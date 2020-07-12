#!/usr/bin/env bash

source .env

sql="$(cat init.sql)"

if ! command -v psql > /dev/null; then
  echo "PostgreSQL is required..."
  exit 1
fi


PGPASSWORD="$POSTGRES_PASSWORD" psql -t -A \
-h "$POSTGRES_HOST" \
-p "$POSTGRES_PORT" \
-U "$POSTGRES_USERNAME" \
-c "$sql"