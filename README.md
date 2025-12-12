## Run local

```sh
./mvnw spring-boot:run

# Docker
./docker/build-image.sh

docker run -d \
  --name db-keep-alive-bcp-app \
  -p 8080:8080 \
  -v /opt/oci_wallet:/opt/oci_wallet \
  -v /opt/wallet_oci_23ai:/opt/wallet_oci_23ai \
  --env-file $(pwd)/build/.env \
  bproenca/db-keep-alive-bcp-app:v4
```

## Test

Using httpie:
```sh
# print host-name and IP
http localhost:8080/db/info

# last rows inserted
http localhost:8080/db/data/tenant/DB-ONE
http localhost:8080/db/data/tenant/DB-TWO
```

## OCI Test

https://httpie.io/
```sh
http https://www.brunobcp.com/db/info
http --follow http://www.brunobcp.com/db/info
```