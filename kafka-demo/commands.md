# Remove the default files

```
rm -rf /tmp/kraft-combined-logs/
```

# Start kafka

```
KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"

bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/server.properties

bin/kafka-server-start.sh config/server.properties
```

# Debug log file

```
./bin/kafka-run-class.sh kafka.tools.DumpLogSegments --print-data-log --files /tmp/kraft-combined-logs/test-topic-0/00000000000000000000.index
```