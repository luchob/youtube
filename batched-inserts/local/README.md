https://www.geeksforgeeks.org/devops/log-all-queries-in-the-official-postgres-docker-image/

```shell
docker cp ./postgresql.conf my_postgres:var/lib/postgresql/data/postgresql.conf 
docker cp ./postgresql.conf 40ec56510a07:var/lib/postgresql/data/postgresql.conf 
```

```shell
docker restart postgres
docker restart 40ec56510a07
```

```shell
docker exec -it my_postgres bash
docker exec -it 40ec56510a07 bash
```

```shell
cd  /var/lib/postgresql/data/log
```

